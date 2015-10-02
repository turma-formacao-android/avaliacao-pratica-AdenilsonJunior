Foram feitas as duas activities, uma de lista e outra de formulário;
Os contatos na lista sao dispostos através do Adapter Customizado criado;
O carregamento da Lista é feito por AsyncTask;
A item de contexto de menu Delete está funcionando perfeitamente. Deletando tanto o contato selecionado, como tambem as tuplas
das tabelas Emails, Telefones e Redes que tem o id do Contato;
A edição foi feita realizada carregando todos os componentes de contato e endereço.
A parte do carregamento dos telefones, redes e emails na edicao de um contato foi inicializado, porem não houve tempo para terminar
A associação dos contatos do app Contacts ja do device com o app criado na avaliacao foi realizada, carregando corretamente informações de nome e celulares,
porem o resto das informações apresentam divergencias(!?)
Foi feito o filtro dos contatos na lista quando estava usando apenas ArrayAdapter para apresentacao dos contatos. Depois que atualizei o ArrayAdapter por um Adapter
customizado, houve muito trabalho trabalho e nao deu pra finalizar.
Os contatos estão sendo cadastrados corretamente, sendo que é esperado que o usuario digite todos os campos corretamente;
As listas de telefones, redes e emails no formalurio foram implementadas corretamente, sendo carregadas assim que é clicado no ImageView +
Foi feito através de ArrayAdapter e uma ListView com ScrollDown.

