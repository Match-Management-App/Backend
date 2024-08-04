package com.match_management.demo.chemicalPartner;

import com.match_management.demo.chemicalPartner.dto.ChemicalResponse;
import com.match_management.demo.user.UserService;
import java.time.LocalDate;
import java.time.Month;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@SpringBootTest
@Transactional
@Rollback
public class ChemicalPartnerTest {

    @Autowired
    UserService userService;

    @Autowired
    ChemicalPartnerService chemicalPartnerService;
    private static Long user1;
    private static Long user2;
    private static Long user3;
    private static Long user4;
    private static Long user5;
    private static Long user6;
    private final static LocalDate matchDate = LocalDate.of(2024, Month.JUNE, 13);

    @BeforeEach
    public void setUp() {
        user1 = userService.create(1L, "수환", "middleFielder");
        user2 = userService.create(2L, "용수", "forward");
        user3 = userService.create(3L, "상화", "middleFielder");
        user4 = userService.create(4L, "용범", "forward");
        user5 = userService.create(5L, "재국", "defender");
        user6 = userService.create(6L, "우규", "middleFielder");
    }

    // 골 넣은 사람과 어시스트한 사람과 골 갯수를 통해, chemical table 생성
    @Test
    public void viewMyBestAssistsChemicalPartner() {
        //given
        chemicalPartnerService.create(user1, user3, 3, matchDate);
        chemicalPartnerService.create(user1, user2, 2, matchDate);
        chemicalPartnerService.create(user1, user5, 4, matchDate);
        chemicalPartnerService.create(user1, user6, 1, matchDate);

        //when
        ChemicalResponse chemicalResponse = chemicalPartnerService.getBestAssistPartner(user1);

        //then
        assertThat(chemicalResponse.getFirst()).isEqualTo("재국");
        assertThat(chemicalResponse.getSecond()).isEqualTo("상화");
        assertThat(chemicalResponse.getThird()).isEqualTo("용수");
    }

    @Test
    public void viewMyBestGoalChemicalPartner() {
        //given
        chemicalPartnerService.create(user3, user1, 3, matchDate);
        chemicalPartnerService.create(user5, user1, 2, matchDate);
        chemicalPartnerService.create(user6, user1, 4, matchDate);
        chemicalPartnerService.create(user2, user1, 1, matchDate);

        //when
        ChemicalResponse names = chemicalPartnerService.getBestGoalPartner(user1);

        //then
        assertThat(names.getFirst()).isEqualTo("우규");
        assertThat(names.getSecond()).isEqualTo("상화");
        assertThat(names.getThird()).isEqualTo("재국");
    }
}
