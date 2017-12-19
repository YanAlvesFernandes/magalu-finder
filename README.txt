Funcionamento do app magalu-finder

Login:

O login � realizado via Facebook API, que efetua o login via a conta do usu�rio no Facebook (caso n�o deseje fornecer os dados para testes, 
apenas remova a LoginActivity e o token de verifica��o na MainActivity)

Listagem de filiais:

- Na MainActivity temos a listagem de filiais, que � uma listView listando os �tens do list_item.xml, que � uma lista personalizada, sendo implementada pela classe FilialAdapter, no pacote Adapter
- No canto inferior direito se encontra o bot�o para ir at� a activity_formul�rio, onde s�o coletados os dados para cadastro das filiais (Descri��o, CEP, Cidade, UF, e Bairro)
- Para acessar o cadastro de produtos deve ser pressionado o registro da filial para exibi��o do menu de contexto, com as op��es de ir para o formul�rio de produtos ou deletar a filial
s
Cadastro de filiais e produtos 

Os cadastro foram implementados com o padr�o MVC, onde os objetos s�o instanciados pelo pacote Model que cont�m as classes Filial e Produto, ja a comunica��o 
com o banco esta no  pacote DAO, com as classes FilialDAO  e ProdutoDAO, respons�veis pelo m�todos de inser��o, busca e exclus�o

- No cadastro de produtos, s�o exigidos os campos Desci��o, valor e uma foto do produto
- O cadastro da foto � o cadastro do caminho da foto no dispositivo, e posteriormente convertido em um bitmap para exibi��o

Localiza��o de filiais

As filiais cadastradas ser�o exibidas na classe MapsActivity, acessada atrav�s do �cone de localiza��o, no menu superior da MainActivity

- Para exibi��o no mapa, a busca � realizado por cidade e estado da filial, a localiza��o � convertida em latitude e longitude e exibida no mapa
- No ponteiro de localiza��o � exibido a Cidade e Estado da filial

