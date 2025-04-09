package data;

import lombok.*;
import org.hd.config.Utilities;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarRegistrationData {

    @Builder.Default
    private String plateNumber = Utilities.getRandomString(3, true, false).toUpperCase() + Utilities.getRandomString(4, false, true);
    @Builder.Default
    private String plateYear = Utilities.getRandomIntegerFromSequence(2015, 2017);

}
