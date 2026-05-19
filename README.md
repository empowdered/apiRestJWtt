# apiRestJWtt

Repositorio histórico de una API REST con autenticación basada en JWT, desarrollado como parte de mis primeros intentos por formalizar una línea de trabajo como desarrollador backend.

Este proyecto representa una etapa inicial de aprendizaje y experimentación con Java Enterprise, servicios REST, persistencia JPA y despliegue sobre servidor de aplicaciones. Más que un producto final, funciona como una fotografía técnica de una fase temprana de evolución profesional.

## Propósito del proyecto

El objetivo principal fue construir una base backend capaz de:

- Exponer servicios REST.
- Autenticar usuarios mediante credenciales.
- Preparar generación y validación de tokens JWT.
- Persistir información usando JPA/Hibernate.
- Trabajar con entidades de dominio como usuarios, perfiles, propiedades y clientes.
- Desplegarse en un entorno Java EE usando WildFly.

## Contexto personal

Este repositorio forma parte de mis primeros ejercicios serios para ordenar una línea de desarrollo backend propia.

En ese momento, el foco no era solamente escribir código funcional, sino comenzar a entender cómo se integraban piezas reales de una arquitectura empresarial:

- servidor de aplicaciones;
- datasource JNDI;
- entidades JPA;
- DAOs;
- servicios de aplicación;
- DTOs;
- autenticación;
- configuración externa;
- persistencia en base de datos.

Visto en retrospectiva, este proyecto marca una etapa donde empecé a pasar desde código aislado hacia una estructura más formal de aplicación backend.

## Stack técnico identificado

- Java
- Java EE / Jakarta EE clásico
- JAX-RS
- JPA 2.1
- Hibernate
- EJB
- PostgreSQL
- WildFly 10.x
- Eclipse IDE / Dynamic Web Project

## Estructura conceptual

El proyecto sigue una separación inicial por responsabilidades:

```text
src/main/java/com/backend/api
├── app/          # Lógica de aplicación / servicios
├── dao/          # Acceso a datos mediante EntityManager y NamedQueries
├── dto/          # Objetos de transferencia de datos
├── entities/     # Entidades JPA
└── constantes/   # Constantes de configuración
