package edu.dosw.lab.agilismo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.Normalizer;

/**
 * Pruebas teóricas de conceptos de Agilismo y Scrum (Parte 1 del laboratorio).
 * Cada test valida que la explicación/documentación en README.md contiene los
 * elementos clave requeridos. Si el README cambia y se pierde un concepto, la
 * prueba fallará sirviendo como guardián de conocimiento.
 */
public class AgilismoTests {
    private static final String README_TEXT = loadReadme();
    private static final String README_NORM = normalize(README_TEXT.toLowerCase());

    private static String loadReadme() {
        Path p = Path.of("README.md");
        try {
            return Files.readString(p, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new IllegalStateException("No se pudo leer README.md para validar conceptos", e);
        }
    }

    private static String normalize(String input) {
        // Elimina acentos para búsquedas robustas
        return Normalizer.normalize(input, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "");
    }

    /**
     * Verifica que el README diferencia correctamente una prueba unitaria (aislada)
     * de una prueba de extremo a extremo (E2E) mencionando alcance total del sistema.
     */
    @Test
    @DisplayName("Prueba Unitaria vs E2E")
    void testUnidadVsE2E() {
    Assertions.assertTrue(README_NORM.contains("prueba unitaria"), "Debe mencionar 'prueba unitaria'");
    Assertions.assertTrue(README_NORM.contains("aislada"), "Debe indicar que es aislada");
    Assertions.assertTrue(README_NORM.contains("prueba e2e") || README_NORM.contains("end-to-end"), "Debe mencionar E2E");
    Assertions.assertTrue(README_NORM.contains("todo el sistema") || README_NORM.contains("todas las capas"), "Debe indicar alcance integral");
    }

    /**
     * Asegura que el README describe la Sprint Retrospective como mecanismo de
     * mejora continua e incluye acción de ajuste/mejoras.
     */
    @Test
    @DisplayName("Sprint Retrospective")
    void testSprintRetrospective() {
    Assertions.assertTrue(README_NORM.contains("sprint retrospective"));
    Assertions.assertTrue(README_NORM.contains("mejora continua"));
    Assertions.assertTrue(README_NORM.contains("ajust") || README_NORM.contains("reforzar"));
    }

    /**
     * Comprueba que se definan claramente Épica, Feature e Historia de Usuario
     * y que exista un ejemplo contextual (Netflix / streaming).
     */
    @Test
    @DisplayName("Épica Feature Historia")
    void testEpicaFeatureHistoria() {
    Assertions.assertTrue(README_NORM.contains("epica"), "Debe definir épica");
    Assertions.assertTrue(README_NORM.contains("feature"), "Debe definir feature");
    Assertions.assertTrue(README_NORM.contains("historia de usuario"), "Debe definir historia de usuario");
    Assertions.assertTrue(README_NORM.contains("netflix") || README_NORM.contains("streaming"), "Debe dar ejemplo de dominio");
    }

    /**
     * Valida que se explica que la cobertura de código (incluyendo referencia a 100%)
     * no garantiza ausencia de errores.
     */
    @Test
    @DisplayName("Cobertura de Código")
    void testCobertura() {
    Assertions.assertTrue(README_NORM.contains("cobertura de codigo"));
    Assertions.assertTrue(README_NORM.contains("100%"));
    Assertions.assertTrue(README_NORM.contains("no garantiza") || README_NORM.contains("no asegura"));
    }

    /**
     * Verifica que el README define el diagrama de casos de uso incluyendo actores,
     * casos de uso, relaciones y su rol en el análisis de requerimientos.
     */
    @Test
    @DisplayName("Casos de Uso")
    void testDiagramaCasosDeUso() {
    Assertions.assertTrue(README_NORM.contains("diagrama de casos de uso"));
    Assertions.assertTrue(README_NORM.contains("actores"));
    Assertions.assertTrue(README_NORM.contains("casos de uso"));
    Assertions.assertTrue(README_NORM.contains("relaciones"));
    Assertions.assertTrue(README_NORM.contains("requerimientos"));
    }

    /**
     * Confirma que se distinguen las herramientas: JUnit (pruebas), JaCoCo (cobertura)
     * y SonarQube (análisis de calidad).
     */
    @Test
    @DisplayName("JUnit / JaCoCo / SonarQube")
    void testHerramientas() {
    Assertions.assertTrue(README_NORM.contains("junit"));
    Assertions.assertTrue(README_NORM.contains("jacoco"));
    Assertions.assertTrue(README_NORM.contains("sonarqube"));
    Assertions.assertTrue(README_NORM.contains("cobertura"));
    Assertions.assertTrue(README_NORM.contains("pruebas"));
    Assertions.assertTrue(README_NORM.contains("calidad"));
    }

    /**
     * Asegura que la sección de Planning Poker menciona ventajas clave: transparencia,
     * compromiso y mitigación de sesgos de jerarquía.
     */
    @Test
    @DisplayName("Planning Poker")
    void testPlanningPoker() {
    Assertions.assertTrue(README_NORM.contains("planning poker"));
    Assertions.assertTrue(README_NORM.contains("transparencia"));
    Assertions.assertTrue(README_NORM.contains("compromiso"));
    Assertions.assertTrue(README_NORM.contains("sesgo") || README_NORM.contains("jefe"));
    }

    /**
     * Verifica la enumeración de los cinco valores de Scrum y que se identifique
     * uno como el más difícil de aplicar (apertura u otra justificación).
     */
    @Test
    @DisplayName("Valores Scrum")
    void testValoresScrum() {
    Assertions.assertTrue(README_NORM.contains("compromiso"));
    Assertions.assertTrue(README_NORM.contains("coraje"));
    Assertions.assertTrue(README_NORM.contains("enfoque"));
    Assertions.assertTrue(README_NORM.contains("apertura"));
    Assertions.assertTrue(README_NORM.contains("respeto"));
    Assertions.assertTrue(README_NORM.contains("suele ser complicada") || README_NORM.contains("mas dificil") || README_NORM.contains("más difícil"));
    }
}
