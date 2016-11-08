INSERT INTO Sexo VALUES ('MASCULINO')
INSERT INTO Sexo VALUES ('FEMENINO')

INSERT INTO EstadoCivil VALUES ('CASADO')
INSERT INTO EstadoCivil VALUES ('SOLTERO')
INSERT INTO EstadoCivil VALUES ('VIUDO')
INSERT INTO EstadoCivil VALUES ('DIVORCIADO')
INSERT INTO EstadoCivil VALUES ('CONCUBINO')

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