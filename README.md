<h1>FIS Workshop</h1>

<b>Rede Wireless:</b> REGUSNETWIFI, senha 80585223

<b>Endereço Openshift: </b>https://master.na37.openshift.opentlc.com
<b>Usuário:</b> rchies-redhat.com / dilopes-redhat.com
<b>Senhas:</b> Redhat123$

oc login https://master.na37.openshift.opentlc.com
mvn fabric8:deploy


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
* Rxemplo de transformação de mensaegens usando o patter dozer()
