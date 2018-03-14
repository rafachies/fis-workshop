<h1>FIS Workshop</h1>

<b>Rede Wireless:</b> REGUSNETWIFI, senha 80585223

<b>Endereço Openshift: </b>https://master.na37.openshift.opentlc.com
<b>Usuário:</b> rchies-redhat.com / dilopes-redhat.com<p>
<b>Senhas:</b> Redhat123$

oc login https://master.na37.openshift.opentlc.com <p>
  
<b>oc project !seunome!</b>
  
mvn fabric8:deploy <p>


<b> Serviço SOA Externo:</b> http://www.webservicex.net/country.asmx?wsdl


<h2>Roteiro do Workshop</h2>

<h3> LAB01 </h3>

* Criação da primeira rota, overview de sua anatomia
* Exemplo do primeiro endpoint consumer, Timer*
* Exemplo de um primeiro EIP, .log()
* Exemplo da construção de um processor customizado

<h3> LAB02 </h3>

* Criação de endpoint consumer REST com DSL
* Utilização do Swagger integrado ao REST DSL
* Exemplo de uso do EIP marshal/unmarshal de JSON
* Exemplo de EIP Bean, chamando beans Spring e seus métodos

<h3> LAB03 </h3>

* Criação de um endpoint consumer par SOAP, usando camel-cxf
* Uso do EIP direct para chamada síncrona entre rotas
* Uso do pattern recipientList() para fazer roteamento dinâmico
* Exemplo de um endpoint producer para SOAP, usando camel-cxf
* Exemplo de um endpoint producer para REST, usando camel-http4
* Exemplo de transformação de mensaegens usando o patter dozer()

<h3> LAB04 </h3>

* Criação de um endpoint producder para fazer queries em banco, camel-sql
* Exemplo de uso do SelectOne com outputClass para fazer binding automatico para POJO via camel-sql
* Exemplo de uso de parameters dentro da query
* Exemplo de uso do camel-jpa para fazer merge de entidades no banco
* Exemplo de uso do camel-jpa para iniciar uma rota a partir de queries no banco (pooling)
* Uso de transação dentro de uma rota

<h3> LAB05 </h3>

* Criação de um endpoint consumer para leitura de arquivo de um siretrio
* Exemplo do pattern CBR para condicionais dentro da rota
* Exemplo de um endpoint producer para escrita de arquivos em disco

<h3> LAB06 </h3>

* Exemplo de tratamento de exceção com try/catch/finally
* Exemplo de tratamento de exceção com patter onException
* Exemplo de decisões do onEception em termos das opções continued e handled

<h3> LAB07 </h3>

* Deployment do nosso workshop no Openshift via Fabric8
* Overview do Pod, Service e console Hawtio
* Criaço de uma Rota do Openshift e teste das rotas do Camel



