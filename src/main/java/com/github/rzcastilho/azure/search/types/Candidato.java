package com.github.rzcastilho.azure.search.types;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.rzcastilho.azure.search.types.annotation.Index;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
@Index("candidatosdev")
public class Candidato {

    @JsonProperty("ID")
    private String id;

    @JsonProperty("DS_TIPO_BASE")
    private String tipoBase;

    @JsonProperty("CD_RA")
    private String ra;

    @JsonProperty("DS_NOME_COMPLETO")
    private String nome;

    @JsonProperty("NR_CPF")
    private String cpf;

    @JsonProperty("NR_RG")
    private String rg;

    @JsonProperty("DS_UNIDADE_MKT")
    private String unidade;

    @JsonProperty("CD_CURSO1")
    private String idCurso;

    @JsonProperty("DS_CURSO1_TRATADO")
    private String curso;

    @JsonProperty("DS_CANAL")
    private String canal;

    @JsonProperty("NR_ANO_PERIODO")
    private String periodoCaptacao;

    @JsonProperty("DS_SITUACAO_ACAD_TRAT_PORTAL_K")
    private String situacao;

    @JsonProperty("DS_MODALIDADE")
    private String modalidade;

    @JsonProperty("CD_UNIDADE_MASTER")
    private String idUnidade;

    @JsonProperty("CD_CONSULTOR")
    private String idConsultor;

    @JsonProperty("DT_INSCRICAO_TRAT")
    @JsonFormat(shape = STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private Date dtInscricao;

    @JsonProperty("DT_MATRICULA_TRAT")
    @JsonFormat(shape = STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private Date dtMatricula;

    @JsonProperty("DS_TABULACAO")
    private String tabulacao;

    @JsonProperty("CD_USUARIO_TABULACAO")
    private String idUsuario;

    @JsonProperty("DT_APROVACAO_TRAT")
    @JsonFormat(shape = STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private Date dtAprovacao;

    @JsonProperty("DS_MARCA_DETALHE")
    private String marca;

    @JsonProperty("DT_PROVA_TRAT")
    @JsonFormat(shape = STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private Date dtProva;

    @JsonProperty("NR_DDD_CELULAR_ENRIQ_1")
    private String dddCelular;

    @JsonProperty("NR_CELULAR_ENRIQ_1")
    private String numeroCelular;

    @JsonProperty("DS_EMAIL_ENRIQ_1")
    private String email;

    @JsonProperty("DT_NASCIMENTO")
    @JsonFormat(shape = STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private Date dtNascimento;

    @JsonProperty("DS_TURNO")
    private String turno;

    @JsonProperty("DS_LOGRADOURO_TRAT_1")
    private String logradouro;

    @JsonProperty("DS_NUMERO_TRAT_1")
    private String numero;

    @JsonProperty("DS_COMPLEMENTO_TRAT_1")
    private String complemento;

    @JsonProperty("DS_BAIRRO_TRAT_1")
    private String bairro;

    @JsonProperty("DS_CIDADE_TRAT_1")
    private String cidade;

    @JsonProperty("DS_UF_TRAT_1")
    private String uf;

    @JsonProperty("NR_CEP")
    private String cep;

    @JsonProperty("NR_DDD_TEL_RESID_ENRIQ_1")
    private String dddTelefoneResidencial;

    @JsonProperty("NR_TEL_RESID_ENRIQ_1")
    private String numeroTelefoneResidencial;

    @JsonProperty("CD_INSCRICAO")
    private String idInscricaoLegado;

    @JsonProperty("CD_ALUNO")
    private String idMatriculaLegado;

    @JsonProperty("CD_UNIDADE_ORIGEM")
    private String idUnidadeOrigem;

    @JsonProperty("DS_SISTEMA_ORIGEM")
    private String sistema;


}
