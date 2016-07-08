$(function(){

        //CADASTRO
        var form = $(".cadastro form");

        var empty = form.find('input');

        $(form).submit(function( event ) {
          if ( empty.val() != "" ) {
            $( ".msg" ).addClass('ok').text( "Após o envio dos dados, verifique seu e-mail. Um link de confirmação serão enviado para que complemente o cadastro com as informações de pagamento." ).show();
            return;
          }

          $( ".msg" ).addClass('error').text( "Todos os campos obrigatoriamente precisam ser preenchidos para continuidade do seu cadastro.").show().delay(5000).fadeOut( 1000 );
          event.preventDefault();
        });


        $(".tipo").on('click', function(){
            $(this).find('.tipoJuridica input').prop('checked');
            if($(".tipoJuridica input").prop('checked')){
                $('.comercial').show();
            }else{
                $('.comercial').hide();
            }
        })
        //COBRANCA
        $('.opcao .pagamento').click(function(){
            $('.opcao').find('.click').removeClass('click');
            $(this).addClass('click');

        })

        $(".tipoCartao").click(function(){
            $('#dadosDebito').fadeOut(400);
            $('#dadosCartao').delay(400).fadeIn();
        })
        $(".tipoDebito").click(function(){
            $('#dadosCartao').fadeOut(400);
            $('#dadosDebito').delay(400).fadeIn();
        })


    })