package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MoneyTest {

    @DisplayName("생성하기")
    @Test
    void create() {
        Money money = new Money(1000);
        assertThat(money).isEqualTo(new Money(1000));
    }

    @DisplayName("입력 금액은 1000원 이상이어야 한다.")
    @Test
    void checkMoney() {
        assertThatIllegalArgumentException()
            .isThrownBy(() -> new Money(0));

        assertThatIllegalArgumentException()
            .isThrownBy(() -> new Money(999));
    }

    @DisplayName("입력금액을 1000으로 나눈 몫만큼 티켓을 만들 수 있다.")
    @Test
    void countTickets() {
        assertThat(new Money(3000).countTickets()).isEqualTo(3);
        assertThat(new Money(4500).countTickets()).isEqualTo(4);
    }

    @DisplayName("구입 금액과 리워드를 이용해 수익률을 계산할 수 있다.")
    @Test
    void calculateProfit() {
        assertThat(new Money(5000).calculateProfit(5000)).isEqualTo(1.00f);
    }
}
