package com.usach.ayudantia;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.usach.ayudantia");

        noClasses()
            .that()
            .resideInAnyPackage("com.usach.ayudantia.service..")
            .or()
            .resideInAnyPackage("com.usach.ayudantia.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.usach.ayudantia.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
