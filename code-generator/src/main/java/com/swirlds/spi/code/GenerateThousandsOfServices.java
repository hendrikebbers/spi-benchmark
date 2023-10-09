package com.swirlds.spi.code;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import com.swirlds.spi.services.ServiceWithId;
import com.swirlds.spi.services.ServiceWithName;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;
import javax.annotation.processing.Generated;
import javax.lang.model.element.Modifier;

public class GenerateThousandsOfServices {

    public static final String PACKAGE_ONE_NAME = "com.swirlds.spi.implementation.thousands.one";
    public static final String PACKAGE_TWO_NAME = "com.swirlds.spi.implementation.thousands.two";

    public static final String SERVICE_WITH_ID_SUFFIX = ServiceWithId.class.getSimpleName();
    public static final String SERVICE_WITH_NAME_SUFFIX = ServiceWithName.class.getSimpleName();

    private static final Path SOURCE_SET_ONE = Path.of("service-implementation-one/src/main/java");
    private static final Path SOURCE_SET_TWO = Path.of("service-implementation-two/src/main/java");

    private static final int NUMBER_OF_SERVICES = 10;

    public static void main(String[] args) throws Exception {

        Files.walk(SOURCE_SET_ONE)
                .sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .forEach(File::delete);

        Files.walk(SOURCE_SET_TWO)
                .sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .forEach(File::delete);

        if(args == null || args.length == 0) {
            generateModule(SOURCE_SET_ONE, PACKAGE_ONE_NAME, "Foo", NUMBER_OF_SERVICES);
            generateModule(SOURCE_SET_TWO, PACKAGE_TWO_NAME, "Bar", NUMBER_OF_SERVICES);
        } else {
            String number = args[0];
            final int numberOfServices = Integer.parseInt(number);
            generateModule(SOURCE_SET_ONE, PACKAGE_ONE_NAME, "Foo", numberOfServices);
            generateModule(SOURCE_SET_TWO, PACKAGE_TWO_NAME, "Bar", numberOfServices);
        }
    }

    private static void generateModule(final Path sourceSet, final String name, String classNamePrefix, final int numberOfServices) {
        final List<JavaFile> withIdFiles = new ArrayList<>();
        final List<JavaFile> withNameFiles = new ArrayList<>();
        IntStream.range(0, numberOfServices).forEach(i -> {
            try {
                final JavaFile serviceWithId = createServiceWithId(name + ".set" + (i % 100),
                        classNamePrefix + i + SERVICE_WITH_ID_SUFFIX);
                serviceWithId.writeTo(sourceSet);
                withIdFiles.add(serviceWithId);

                final JavaFile serviceWithName = createServiceWithName(name + ".set" + (i % 100),
                        classNamePrefix + i + SERVICE_WITH_NAME_SUFFIX);
                serviceWithName.writeTo(sourceSet);
                withNameFiles.add(serviceWithName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        createModuleInfo(sourceSet, name, withIdFiles, withNameFiles);
    }

    private static void createModuleInfo(final Path sourceSet, final String name, final List<JavaFile> withIdFiles, final List<JavaFile> withNameFiles) {
        final Path moduleInfoPath = sourceSet.resolve("module-info.java");
        try (FileWriter fileWriter = new FileWriter(moduleInfoPath.toFile())) {
            fileWriter.write("module " + name + " {\n");
            fileWriter.write("    requires com.swirlds.spi.services;\n");
            fileWriter.write("    requires java.compiler;\n");
            fileWriter.write("    requires com.google.auto.service;\n");
            if(!withIdFiles.isEmpty()) {
                withIdFiles.stream().map(f -> f.packageName + "." + f.typeSpec.name)
                        .reduce((a, b) -> a + ", " + b)
                        .ifPresent(s -> {
                            try {
                                fileWriter.write("    provides " + ServiceWithId.class.getName() + " with " + s + ";\n");
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        });
            }
            if(!withNameFiles.isEmpty()) {
                withNameFiles.stream().map(f -> f.packageName + "." + f.typeSpec.name)
                        .reduce((a, b) -> a + ", " + b)
                        .ifPresent(s -> {
                            try {
                                fileWriter.write("    provides " + ServiceWithName.class.getName() + " with " + s + ";\n");
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        });
            }
            fileWriter.write("}\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static JavaFile createServiceWithId(final String packageName, final String className) throws IOException {
        MethodSpec main = MethodSpec.methodBuilder("getId")
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(Override.class)
                .returns(UUID.class)
                .addStatement("return $T.randomUUID()", UUID.class)
                .build();

        AnnotationSpec generatedAnnotation = AnnotationSpec.builder(Generated.class)
                .addMember("value", "$S", GenerateThousandsOfServices.class.getName())
                .addMember("date", "$S", DateTimeFormatter.ISO_DATE_TIME.format(LocalDateTime.now()))
                .build();

        AnnotationSpec autoServiceAnnotation = AnnotationSpec.builder(AutoService.class)
                .addMember("value", "$T.class", ServiceWithId.class)
                .build();

        TypeSpec helloWorld = TypeSpec.classBuilder(className)
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addAnnotation(generatedAnnotation)
                .addAnnotation(autoServiceAnnotation)
                .addSuperinterface(ServiceWithId.class)
                .addMethod(main)
                .build();

        JavaFile javaFile = JavaFile.builder(packageName, helloWorld)
                .build();
        return javaFile;
    }

    private static JavaFile createServiceWithName(final String packageName, final String className) throws IOException {
        MethodSpec main = MethodSpec.methodBuilder("getName")
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(Override.class)
                .returns(String.class)
                .addStatement("return $S", UUID.randomUUID().toString())
                .build();

        AnnotationSpec generatedAnnotation = AnnotationSpec.builder(Generated.class)
                .addMember("value", "$S", GenerateThousandsOfServices.class.getName())
                .addMember("date", "$S", DateTimeFormatter.ISO_DATE_TIME.format(LocalDateTime.now()))
                .build();

        AnnotationSpec autoServiceAnnotation = AnnotationSpec.builder(AutoService.class)
                .addMember("value", "$T.class", ServiceWithName.class)
                .build();

        TypeSpec helloWorld = TypeSpec.classBuilder(className)
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addSuperinterface(ServiceWithName.class)
                .addAnnotation(generatedAnnotation)
                .addAnnotation(autoServiceAnnotation)
                .addMethod(main)
                .build();

        JavaFile javaFile = JavaFile.builder(packageName, helloWorld)
                .build();
        return javaFile;
    }
}
