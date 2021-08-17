package nl.tudelft.cse1110.grader.execution.step;

import nl.tudelft.cse1110.IntegrationTestBase;
import org.junit.jupiter.api.Test;

import static nl.tudelft.cse1110.ResultTestAssertions.*;
import static nl.tudelft.cse1110.ExecutionStepHelper.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class RunMetaTestsStepTest extends IntegrationTestBase {

    @Test
    void testAllMetaTestsPassing() {
        String result = run(onlyMetaTests(), "NumberUtilsAddLibrary", "NumberUtilsAddOfficialSolution", "NumberUtilsAddConfiguration");

        assertThat(result)
                .has(metaTests(4))
                .has(metaTestsPassing(4));
    }

    @Test
    void testSomeMetaTestFailing() {
        String result = run(onlyMetaTests(), "NumberUtilsAddLibrary", "NumberUtilsAddAllTestsPass", "NumberUtilsAddConfiguration");

        assertThat(result)
                .has(metaTests(4))
                .has(metaTestsPassing(1))
                .has(metaTestFailing("AppliesMultipleCarriesWrongly"))
                .has(metaTestFailing("DoesNotApplyCarryAtAll"))
                .has(metaTestFailing("DoesNotApplyLastCarry"));
    }

    @Test
    void testAllMetaTestsFailing() {
        String result = run(onlyMetaTests(), "NumberUtilsAddLibrary", "NumberUtilsNoTests", "NumberUtilsAddConfiguration");

        assertThat(result)
                .has(metaTests(4))
                .has(metaTestsPassing(0))
                .has(metaTestFailing("AppliesMultipleCarriesWrongly"))
                .has(metaTestFailing("DoesNotApplyCarryAtAll"))
                .has(metaTestFailing("DoesNotApplyLastCarry"))
                .has(metaTestFailing("DoesNotCheckNumbersOutOfRange"));
    }

    @Test
    void testMetaWhenMultipleClassesInLibrary() {
        String result = run(onlyMetaTests(), "SoftWhereLibrary", "SoftWhereMissingTests", "SoftWhereConfig");

        assertThat(result)
                .has(metaTests(4))
                .has(metaTestsPassing(3))
                .has(metaTestFailing("DoesNotCheckInvalidTripId"));
    }

    @Test
    void testMetaWhenMultipleClassesInSolution() {
        String result = run(onlyMetaTests(), "ArrayUtilsIndexOfLibrary", "ArrayUtilsIndexOfJQWikPassing", "ArrayUtilsIndexOfJQWikConfig");

        assertThat(result)
                .has(metaTests(3))
                .has(metaTestsPassing(3));
    }

}