# Introduction

 este proyecto es version de prueba, donde se deseas optimixar una lista de comprar (problema de la mochila), pero se publica WS que permiten
 introducir la lista y el monto maximo del presupuesto, este proyecto fue implementado en java 1.8, usando NETbeans como IDE  de desarrollo, y MAVEN como estructura
 y windows 10 como SO
 
 # Getting Started
 1.	Installation process
 
Para el correcto funcionmiento de este proyecto, debe ser descargado en un carpeta vacia en la maquina local, y abierto con netbeans o en su defecto importado con eclipse

2.	Software dependencies

  2.1 hacer clean and build
      Maven, se encargara de bajar la dependiencias necesarias, en caso de que tenga problemas en bajar alguna esta debe descargse manualmente y colocarse en la carpeta
      C:\Users\*usurioPC*\.m2\repository
  2.2 hacer run
      el sistema desplegarar correctamente si en paso 2.1, no arrojo errores.

3.	Latest releases

version 1.0

4.	API references

# Build and Test
una vez el proyecto este arriba, soponderemos que esta a 
IP es localhost y que el puerto 7001

tendremos a siguiente URl

http://localhost:7001/ML-ROCHADMRest/

para consumir el servicio

se debe usar la siguiente URL (POST)

http://localhost:7001/ML-ROCHADMRest/ws/coupon


Y se debe enviar un json con la siguiente estructura (valores de ejemplo)
{
    "item_ids": ["MLA1","MLA2","MLA3","MLA4","MLA5","MLA6","MLA1"],
    "amount":440
}

donde "item_ids" lista de items a enviar
 "amount" monto maximo a usar
si ha funcionado correctamente, devolvera:

{
    "item_ids": [
        "MLA1"
    ],
    "total": "220.0"
}

donde "item_ids" lista de items a comprados
 "total" monto maximo a usado
