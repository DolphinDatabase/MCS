<template>
    <link href='https://fonts.googleapis.com/css?family=Nunito' rel='stylesheet'/>
    <link href='https://fonts.googleapis.com/css?family=Inter' rel='stylesheet'/>
    <div id="chamadoCard">
        <div id="esquerda">
            <h2>Informações do Chamado</h2>
            <br>
            <h4>Situação:</h4>
            <br>
            <h4>Criado em:</h4>
            <br>
            <div class="endereco">
                <h4>Cep:</h4> <h4>Número:</h4>
            </div>
            <br>
            <h4>Endereço:</h4>
            <br>
            <h4>Complemento:</h4>
            <br>
            <h4>Bairro:</h4>
            <br>
            <h4>Cidade:</h4>
            <br>
            <h4>Estado:</h4>
        </div>
        <div id="direita">
            <h2>Expansões do Chamado</h2>
            <br>
            <div class="endereco">
                <h4>Identificação:</h4> <h4>Cliente:</h4>
            </div>
            <div class="endereco">
            <h1>#01 Lancha com casco furado e entrando água</h1>
            <p>Kauã Borgarelli</p>
            </div>
            <br>
            <h4>Descrição:</h4>
            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
            Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.    
            </p>
            <div id="botoes">
                <button id="problemas"> 
                    Falhas e Soluções
                </button>
                <button id="equipamento">
                    Equipamentos
                </button>
                <button id="Orçamento">
                    Orçamento
                </button>
            </div>
        </div>
    </div> 
    
</template>
 <script>
 export default {
        name:"Relatorio"
    }

</script>
 <style scoped>
    #chamadoCard{
        display: flex;       
    }
    #esquerda{
        /* display: center; */
        word-break:normal;
        width: 60%;
        align-items: column;
        /*height: 550px;*/
        padding: 10px 10px;
        /*background: #FFFFFF;*/
        box-shadow: 1px 0px 20px rgba(0, 0, 0, 0.25);
        border-radius: 10px;
    }
    #direita{
        /*width: 55%;*/
        /*height: 450px;*/
        word-break: normal;
        padding:10px 10px;
        /*box-shadow: 1px 0px 20px rgba(0, 0, 0, 0.25);*/
        margin-left: 8px;
        /*background: #FFFFFF;*/
    }
    .endereco{
        display: flex;
        align-items: center;
        justify-content: space-between;
    }
    p{font-family: 'Inter';
        font-style: normal;
        font-weight: 350;
        font-size: 15px;
        line-height: 23px;

        color: #757575;
    }
    h2{
        font-family: 'Nunito';
        font-style: normal;
        font-weight: 700;
        font-size: 22px;
        line-height: 30px;
        display: flex;
        align-items: center;
        color: #757575;
    }
    h4{
        font-family: 'Nunito';
        font-style: normal;
        font-weight: 700;
        font-size: 16px;
        line-height: 22px;
        display: flex;
        align-items: center;
        color: #3F3F3F;
    }
    #botoes{
        margin-top: 10px;
    }
    #problemas{
        box-sizing: border-box;
        position: flex;
        width: 545px;
        height: 60px;
        color:#F56C6C;
        margin-bottom: 30px;
        background: #FEF0F0;
        border: 2px solid #F56C6C;
        border-radius: 10px;
        font-family: 'Nunito';
        font-style: normal;
        font-weight: 700;
        font-size: 20px;
        line-height: 27px;       
    }
    #equipamento{
        box-sizing: border-box;
        position: flex;
        width: 545px;
        height: 60px;
        margin-bottom: 30px;
        background: #FDF6EC;
        border: 2px solid #E6A23C;
        border-radius: 10px;
        font-family: 'Nunito';
        font-style: normal;
        font-weight: 700;
        font-size: 20px;
        line-height: 27px;
        color: #E6A23C;
    }
    #Orçamento{
        box-sizing: border-box;
        width: 545px;
        height: 60px;
        background: #F0F9EB;
        border: 2px solid #67C23A;
        border-radius: 10px;
        font-family: 'Nunito';
        font-style: normal;
        font-weight: 700;
        font-size: 20px;
        line-height: 27px;
        color: #67C23A;
    }
</style>
  