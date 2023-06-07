## Trabalho avaliativo II Unidade

Tema - Fila de doação de orgãos

Grupo: 
Carlos Felipe Miranda Xavier
Rogério Barreto
Ana Souza
Thallys
Luiz Castro

# Especificações

TEMA IX (TRANSPLANTE DE ÓRGÃOS)

Neste projeto, pretende-se cadastrar pacientes à espera de transplante de órgãos considerando-se ordem cronológica de entrada. No
ato do cadastro, deverão ser fornecidos diversos dados, a saber: a) nome; b) CPF; c) sexo; d) data de nascimento; e e) órgão a ser
transplantado. O uso de coleções genéricas concretas para a manipulação de filas é obrigatório. Além da operação de cadastro
de paciente em fila de transplante, a aplicação desenvolvida deve dispor ainda das seguintes funcionalidades (a serem acessadas a
qualquer momento) e/ou restrições, a saber:
• Indicação de órgão a ser transplantado considerando alguma das seguintes opções: a) coração; b) rim; c) pulmão; e d) fígado;
• Impedimento de existência de 2 (dois) ou mais pacientes com o mesmo número de CPF;
• Registro de data e horário de inclusão em fila de transplante, quando do cadastro de novo paciente;
• Chamada de próximo paciente de tal modo que ele deve ser removido da fila de transplante, seguindo-se a isso exibição de
tempo de permanência naquela fila em dias, horas e minutos na fila;
• Consulta de dados de próximo cliente a ser chamado, bem como tempo de permanência em dias, horas e minutos, até então, na
fila de transplante (sobre os dados do paciente, são aqueles informados no ato do seu cadastro);
• Consultas de caráter estatístico, a saber: a) quantidade de pacientes em espera por tipo de transplante; b) percentual, por tipo de
transplante, de pacientes em espera; c) tempo médio de permanência em fila de espera por tipo de transplante.

1 - Menu de seleção - 
cadastro de paciente
chamar proximo paciente
consultar dados do próximo paciente
consulta estatística
    a) quantidade de pacientes em espera por tipo de transplante;
    b) percentual, por tipo de
transplante, de pacientes em espera
    c) tempo médio de permanência em fila de espera por tipo de transplante.
encerrar programa

2 - Verificação de CPF repetido na fila de pacientes - Luiz
Impedimento de existência de 2 (dois) ou mais pacientes com o mesmo número de CPF;
pode ser feito percorrendo a fila com um for e verificando se já existe paciente cadastro com o cpf informado

3 - Manipulação e calculo de datas
Implementar um metodo para calcular a diferenca de tempo entre a data de cadastro e a data de saída do paciente da fila

4 - Cadastro de paciente - Carlos

5 -  Salvar e recuperar dados de arquivo txt - Thallys

6 - Consulta de dados do próximo paciente (último da fila) - Carlos

7 - quantidade de pacientes em espera por tipo de transplante; Luiz
pode ser feito com for, assim como a verificação de cpf

8 - percentual, por tipo de transplante, de pacientes em espera;  

9 - tempo médio de permanência em fila de espera por tipo de transplante. - Thallys
Instanciar uma lista com todos 

10 - Refatoração do código - Thallys