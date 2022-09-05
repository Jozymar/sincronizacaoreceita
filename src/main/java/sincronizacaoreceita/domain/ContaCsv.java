package sincronizacaoreceita.domain;

import lombok.*;

/**
 * @author Jozymar<jozymarsoares@gmail.com>
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ContaCsv {

    private String agencia;

    private String conta;

    private String saldo;

    private String status;

    private Boolean resultado;
}
