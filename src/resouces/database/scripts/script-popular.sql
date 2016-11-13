USE Empresa
INSERT INTO Sexo VALUES ('Masculino')
INSERT INTO Sexo VALUES ('Femenino')

INSERT INTO EstadoCivil VALUES ('Casado')
INSERT INTO EstadoCivil VALUES ('Soltero')
INSERT INTO EstadoCivil VALUES ('Viudo')
INSERT INTO EstadoCivil VALUES ('Divorciado')
INSERT INTO EstadoCivil VALUES ('Concubino')

INSERT INTO Nivel VALUES ('Administrador')
INSERT INTO Nivel VALUES ('Invitado')

INSERT INTO TipoEmpleado VALUES ('Gerente')
INSERT INTO TipoEmpleado VALUES ('Junior')

INSERT INTO TipoVivienda VALUES ('Casa')
INSERT INTO TipoVivienda VALUES ('Departamento')

INSERT INTO [dbo].[Usuario]
           ([Nombre],[Contraseña],[Email],[IdNivel])
     VALUES
           ('Admin','1933','cursosjava1@gmail.com',1)

GO