# TpFinalRedes

# Integrantes: 

# Como ejecutar:
  abra una consola como cmd
  cd [Raiz del proyecto]\out\artifacts\MainServer_jar
  java -jar MainServer.jar
  
  abra otra consola cmd 
  cd [Raiz del proyecto]\out\artifacts\MainClient_jar
  java -jar MainClient.jar
  
  o utilice una app telnet (en nuestro caso utilizamos Mobiel Telnet para android)

  Una vez que se ejecute algun cliente, debe ir a la consola del servidor, elegir si el cliente es "java (consola)" o "telnet (app mobile)" 
  ahora puede mandar un mensaje desde el cliente, y contestarle desde el servidor
  


Buenas Manuel,

Por favor informa a tus compañeros la nota obtenida y devolución.

Su nota en el trabajo es 8 (ocho).
Les paso la devolución del trabajo:
- El trabajo cumple con los requerimientos pedidos
- Agregaron comentarios en el código para poder llevar a cabo un seguimiento facil y rapido del mismo
- Permite más de un cliente conectarse.
- Preguntas:  Están correctas , solo algunos conceptos que faltaron
1. Endpoints:  Cada conexión está formada por  -Una dirección que indica dónde se puede encontrar el punto de conexión (IP). -Un enlace que especifica cómo un se puede comunicar un cliente con el punto de conexión (Puerto). -Un contrato que identifica las operaciones disponibles (Ejemplo /user/123 , obtiene la información del usuario con la ID 123). -Un conjunto de comportamientos que especifican detalles de implementación local del punto de conexión (Este último puede no existir)
2. Diferencia entre TPC y UDP: Aca esta correcto lo que comentaron, pero me hubiera gustado que hubieran relacionado los conceptos de orientado/ no orientado a la conexión ya que aplica con los protocolos mencionados.  El protocolo TCP es un protocolo de transporte orientado a conexión, mientras que el protocolo UDP no lo es.

Cualquier duda me consultan, 

Saludos,
Leandro
