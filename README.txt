Funcionamento do app magalu-finder

Login:

O login é realizado via Facebook API, que efetua o login via a conta do usuário no Facebook (caso não deseje fornecer os dados para testes, 
apenas remova a LoginActivity e o token de verificação na MainActivity)

Listagem de filiais:

- Na MainActivity temos a listagem de filiais, que é uma listView listando os ítens do list_item.xml, que é uma lista personalizada, sendo implementada pela classe FilialAdapter, no pacote Adapter
- No canto inferior direito se encontra o botão para ir até a activity_formulário, onde são coletados os dados para cadastro das filiais (Descrição, CEP, Cidade, UF, e Bairro)
- Para acessar o cadastro de produtos deve ser pressionado o registro da filial para exibição do menu de contexto, com as opções de ir para o formulário de produtos ou deletar a filial
s
Cadastro de filiais e produtos 

Os cadastro foram implementados com o padrão MVC, onde os objetos são instanciados pelo pacote Model que contém as classes Filial e Produto, ja a comunicação 
com o banco esta no  pacote DAO, com as classes FilialDAO  e ProdutoDAO, responsáveis pelo métodos de inserção, busca e exclusão

- No cadastro de produtos, são exigidos os campos Descição, valor e uma foto do produto
- O cadastro da foto é o cadastro do caminho da foto no dispositivo, e posteriormente convertido em um bitmap para exibição

Localização de filiais

As filiais cadastradas serão exibidas na classe MapsActivity, acessada através do ícone de localização, no menu superior da MainActivity

- Para exibição no mapa, a busca é realizado por cidade e estado da filial, a localização é convertida em latitude e longitude e exibida no mapa
- No ponteiro de localização é exibido a Cidade e Estado da filial

