CREATE TABLE IF NOT EXISTS tipo_usuario
( 
	id_tipo_usuario INTEGER,
	tipo_usuario VARCHAR(255) NOT NULL,
	PRIMARY KEY (id_tipo_usuario)
);

CREATE TABLE IF NOT EXISTS usuario
( 
	id_usuario INTEGER,
	usuario VARCHAR(255) NOT NULL,
	senha VARCHAR(255) NOT NULL,
	nome VARCHAR(255),
	cpf VARCHAR(20) NOT NULL,
	telefone INTEGER,
	data_nascimento DATE,
	email VARCHAR(255) NOT NULL,
	id_tipo_usuario INTEGER,
	PRIMARY KEY (id_usuario),
	FOREIGN KEY (id_tipo_usuario) REFERENCES tipo_usuario (id_tipo_usuario)
);

CREATE TABLE IF NOT EXISTS tipo_denuncia
( 
	id_tipo_denuncia SERIAL,
	tipo_denuncia VARCHAR(255) NOT NULL,
	PRIMARY KEY (id_tipo_denuncia)
);

CREATE TABLE IF NOT EXISTS tipo_animal
( 
	id_tipo_animal INTEGER,
	tipo_animal VARCHAR(255) NOT NULL,
	PRIMARY KEY (id_tipo_animal)
);

CREATE TABLE IF NOT EXISTS condicao
( 
	id_condicao INTEGER,
	condicao VARCHAR(255) NOT NULL,
	PRIMARY KEY (id_condicao)
);

CREATE TABLE IF NOT EXISTS porte
( 
	id_porte INTEGER,
	porte VARCHAR(255) NOT NULL,
	PRIMARY KEY (id_porte)
);

CREATE TABLE IF NOT EXISTS pelo
( 
	id_pelo INTEGER,
	pelo VARCHAR(255) NOT NULL,
	PRIMARY KEY (id_pelo)
);

CREATE TABLE IF NOT EXISTS coloracao
( 
	id_coloracao INTEGER,
	coloracao VARCHAR(255) NOT NULL,
	PRIMARY KEY (id_coloracao)
);

CREATE TABLE IF NOT EXISTS denuncia
( 
	id_denuncia INTEGER,
	cep VARCHAR(20) NOT NULL,
	descricao VARCHAR(1000) NOT NULL,
	endereco VARCHAR(255),
	referencia VARCHAR(255),
	quantidade INTEGER,
	id_usuario INTEGER,
	id_tipo_denuncia INTEGER,
	id_pelo INTEGER,
	id_coloracao INTEGER,
	id_tipo_animal INTEGER,
	id_porte INTEGER,
	id_condicao INTEGER,
	PRIMARY KEY (id_denuncia),
	FOREIGN KEY (id_usuario) REFERENCES usuario (id_usuario),
	FOREIGN KEY (id_tipo_denuncia) REFERENCES tipo_denuncia (id_tipo_denuncia),
	FOREIGN KEY (id_pelo) REFERENCES pelo (id_pelo),
	FOREIGN KEY (id_coloracao) REFERENCES coloracao (id_coloracao),
	FOREIGN KEY (id_tipo_animal) REFERENCES tipo_animal (id_tipo_animal),
	FOREIGN KEY (id_porte) REFERENCES porte (id_porte),
	FOREIGN KEY (id_condicao) REFERENCES condicao (id_condicao)
);

CREATE TABLE IF NOT EXISTS resgate
( 
	id_resgate INTEGER,
	descricao VARCHAR(1000) NOT NULL,
	id_usuario INTEGER,
	id_denuncia INTEGER,
	id_tipo_animal INTEGER,
	id_condicao INTEGER,
	id_porte INTEGER,
	id_coloracao INTEGER,
	PRIMARY KEY (id_resgate),
	FOREIGN KEY (id_usuario) REFERENCES usuario (id_usuario),
	FOREIGN KEY (id_denuncia) REFERENCES denuncia (id_denuncia),
	FOREIGN KEY (id_tipo_animal) REFERENCES tipo_animal (id_tipo_animal),
	FOREIGN KEY (id_condicao) REFERENCES condicao (id_condicao),
	FOREIGN KEY (id_porte) REFERENCES porte (id_porte),
	FOREIGN KEY (id_coloracao) REFERENCES coloracao (id_coloracao)
);