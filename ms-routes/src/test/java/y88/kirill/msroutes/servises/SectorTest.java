package y88.kirill.msroutes.servises;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SectorTest {

//    @TestConfiguration
//    static class SectorTestTestConfiguration{
//        @Bean
//        public Sector sector(){
//            return new Sector();
//        }
//    }

    // @Autowired
    private Sector sector;

    @BeforeAll
    public void init(){
        sector = new Sector();
    }


    @ParameterizedTest
    @DisplayName("Sector - getSectorByRadius")
    @MethodSource("mapSectorDate")
    public void getSectorByRadiusTest(InputData inputData, OutputData outputData){
        Assertions.assertArrayEquals(
                sector.getSectorByRadius(inputData.centerLongitude,inputData.centerLatitude, inputData.radius),
                outputData.sector
        );
    }

    private  Stream<Arguments> mapSectorDate(){
        return Stream.of(
                Arguments.of(new InputData(38.960688, 45.025794, 5000),
                             new OutputData(new double[]{38.897274850649346,
                                            45.07078473190922,
                                            39.02410114935065,
                                            44.980803268090774})),
                Arguments.of(new InputData(38.960688, 45.025794, 5),
                        new OutputData(new double[]{38.96062458685065,
                                45.025838990731906,
                                38.960751413149346,
                                45.02574900926809})),
                Arguments.of(new InputData(38.973337, 45.023144, 50),
                        new OutputData(new double[]{38.972702868506495,
                                45.023593907319096,
                                38.97397113149351,
                                45.02269409268091})),
                Arguments.of(new InputData(38.973337, 45.023144, 5000),
                        new OutputData(new double[]{38.90992385064935,
                                45.068134731909225,
                                39.03675014935065,
                                44.97815326809078}))
        );

    }


    private class InputData{
        double centerLongitude;
        double centerLatitude;
        int radius;

        public InputData(double centerLongitude, double centerLatitude, int radius) {
            this.centerLongitude = centerLongitude;
            this.centerLatitude = centerLatitude;
            this.radius = radius;
        }
    }

    private class OutputData{
        double [] sector;

        public OutputData(double [] sector) {
            this.sector = sector;
        }
    }

}
