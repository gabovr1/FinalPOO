# Enviayá

Sistema automatizado para la gestión de despachos de mensajería en moto para la empresa Enviayá en Medellín.

## Explicacion

El sistema utiliza modelado y abstraccion en paquete y atributos como final o numeroguia, peso y zona y calcula su precion en metodos abstractos segun los diferentes tipos de servicio, tambien una clase util para el formato EY de las guias, 6 exceptions para diferentes casos personalizados, arroja mensaje dependiendo de errores como peso invalido o exceso de peso, ademas de la clase despacho que guarda los paquetes en un list (coleccion) para cumplir el orden de entrega requerido, organiza tambien alfabeticamente por numero de guia

## Principios SOLID Aplicados

### 1. Open-Closed Principle
* Se aplica en la jerarquia de clases de los diferentes tipos de paquetes y en calculo de los costos.
* se aplica porque el sistema está abierto a la extensión, por ejemplo, si el proximo año la gerente añade un servicio nuevo con sus propias reglas de costo, pero cerrado a la modificación, solo se necesitaria crear una nueva clase que salga sin cambiar el codigo en las otras clases.

### 2. Single Responsibility Principle
* Se aplica en la clase util.
* el formato de guias "EY"esta separada de los modelos de paquetes y esta en una clase dedicada solamente a tareas de validacion
