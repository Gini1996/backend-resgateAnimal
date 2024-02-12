CREATE TABLE IF NOT EXISTS TipoUsuario
( 
	idTipoUsuario INTEGER,
	tipoUsuario VARCHAR(255) NOT NULL,
	PRIMARY KEY (idTipoUsuario)
);

CREATE TABLE IF NOT EXISTS Usuario
( 
	idUsuario INTEGER,
	usuario VARCHAR(255) NOT NULL,
	senha VARCHAR(255) NOT NULL,
	nome VARCHAR(255),
	cpf VARCHAR(20) NOT NULL,
	telefone INTEGER,
	dataNascimento DATE,
	email VARCHAR(255) NOT NULL,
	idTipoUsuario INTEGER,
	PRIMARY KEY (idUsuario),
	FOREIGN KEY (idTipoUsuario) REFERENCES TipoUsuario (idTipoUsuario)
);

CREATE TABLE IF NOT EXISTS TipoDenuncia
( 
	idTipoDenuncia INTEGER,
	tipoDenuncia VARCHAR(255) NOT NULL,
	PRIMARY KEY (idTipoDenuncia)
);

CREATE TABLE IF NOT EXISTS TipoAnimal
( 
	idTipoAnimal INTEGER,
	tipoAnimal VARCHAR(255) NOT NULL,
	PRIMARY KEY (idTipoAnimal)
);

CREATE TABLE IF NOT EXISTS Condicao
( 
	idCondicao INTEGER,
	condicao VARCHAR(255) NOT NULL,
	PRIMARY KEY (idCondicao)
);

CREATE TABLE IF NOT EXISTS Porte
( 
	idPorte INTEGER,
	porte VARCHAR(255) NOT NULL,
	PRIMARY KEY (idPorte)
);

CREATE TABLE IF NOT EXISTS Pelo
( 
	idPelo INTEGER,
	pelo VARCHAR(255) NOT NULL,
	PRIMARY KEY (idPelo)
);

CREATE TABLE IF NOT EXISTS Coloracao
( 
	idColoracao INTEGER,
	coloracao VARCHAR(255) NOT NULL,
	PRIMARY KEY (idColoracao)
);

CREATE TABLE IF NOT EXISTS Denuncia
( 
	idDenuncia INTEGER,
	cep VARCHAR(20) NOT NULL,
	descricao VARCHAR(1000) NOT NULL,
	endereco VARCHAR(255),
	referencia VARCHAR(255),
	quantidade INTEGER,
	idUsuario INTEGER,
	idTipoDenuncia INTEGER,
	idPelo INTEGER,
	idColoracao INTEGER,
	idTipoAnimal INTEGER,
	idPorte INTEGER,
	idCondicao INTEGER,
	PRIMARY KEY (idDenuncia),
	FOREIGN KEY (idUsuario) REFERENCES Usuario (idUsuario),
	FOREIGN KEY (idTipoDenuncia) REFERENCES TipoDenuncia (idTipoDenuncia),
	FOREIGN KEY (idPelo) REFERENCES Pelo (idPelo),
	FOREIGN KEY (idColoracao) REFERENCES Coloracao (idColoracao),
	FOREIGN KEY (idTipoAnimal) REFERENCES TipoAnimal (idTipoAnimal),
	FOREIGN KEY (idPorte) REFERENCES Porte (idPorte),
	FOREIGN KEY (idCondicao) REFERENCES Condicao (idCondicao)
);

CREATE TABLE IF NOT EXISTS Resgate
( 
	idResgate INTEGER,
	descricao VARCHAR(1000) NOT NULL,
	idUsuario INTEGER,
	idDenuncia INTEGER,
	idTipoAnimal INTEGER,
	idCondicao INTEGER,
	idPorte INTEGER,
	idColoracao INTEGER,
	PRIMARY KEY (idResgate),
	FOREIGN KEY (idUsuario) REFERENCES Usuario (idUsuario),
	FOREIGN KEY (idDenuncia) REFERENCES Denuncia (idDenuncia),
	FOREIGN KEY (idTipoAnimal) REFERENCES TipoAnimal (idTipoAnimal),
	FOREIGN KEY (idCondicao) REFERENCES Condicao (idCondicao),
	FOREIGN KEY (idPorte) REFERENCES Porte (idPorte),
	FOREIGN KEY (idColoracao) REFERENCES Coloracao (idColoracao)
);