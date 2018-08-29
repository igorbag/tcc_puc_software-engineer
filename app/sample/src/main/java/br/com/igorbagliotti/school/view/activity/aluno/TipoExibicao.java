package br.com.igorbagliotti.school.view.activity.aluno;

import org.parceler.Parcel;

/**
 * Created by Igor Rotondo Bagliotti on 28/08/2018.
 */
@Parcel(analyze = {TipoExibicao.class})
public enum TipoExibicao {
    VISUALIZAR, EDITAR;
}
