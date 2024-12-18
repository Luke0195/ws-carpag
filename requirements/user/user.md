# Usuário
> ### Casos de Sucesso 
✅
 
1. ✅ Recebe uma requisição do tipo <b> **POST** </b>, na rota api/users.
2. ✅ Valida os dados obrigatórios **nome**, **email**.
3. ✅ Valida se o **email** informado é valido.
4. ✅ Valida se o **email** já está cadastrado no banco de dados.
5. ✅ Cria um usuário com os dados informados.
6. ✅ Retorna **201** com o id e os dados informados pelo usuário.
7. ✅ Retorna **200** com a lista de usuários.

> ### Exceções 

1. ✅ Retorna erro **404** se API não existir.
2. ✅ Retorna erro **400** se o nome e email não forem informados.
3. ✅ Retorna erro **400** se o email fornecido não for valido.
4. ✅ Retorna erro **400** se o e-mail informado já estiver sendo usado.
5. ✅ Retorna erro **500** se der erro ao criar uma conta do usuário.
6. ✅ Retorna erro **500** se der erro tentar carregar a lista do usuários. 