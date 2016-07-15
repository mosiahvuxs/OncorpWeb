/* Máscaras */

$(function() {
    $('.data').mask('00/00/0000');
    $('.hora').mask('00:00:00');
    $('.data_hora').mask('00/00/0000 00:00:00');
    $('.cep').mask('00000-000');
    $('.telefone').mask('00009-0000');
    $('.telefone_com_ddd').mask('(00) 00009-0000');
    $('.ip').mask('099.099.099.099');
    $('.percentual').mask('##0,00%', {reverse: true});
    $('.mascara').mask("00/00/0000", {placeholder: "__/__/____"});
    $('.cpf').mask('000.000.000-00', {reverse: true});
    $('.cnpj').mask('00.000.000/0000-00', {reverse: true});
    $('.moeda').mask('#.##0,00', {reverse: true, maxlength: false});
  });