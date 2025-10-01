-- USUARIOS
INSERT INTO tb_usuario (nome, email, senha, role)
VALUES ('Mateus Souza', 'mateus.souza@motoconnect.com', 'senha123', 'USER');

INSERT INTO tb_usuario (nome, email, senha, role)
VALUES ('Cauan Passos', 'cauan.passos@motoconnect.com', 'admin1234', 'ADMIN');

UPDATE tb_usuario
SET senha = '$2a$10$gsg2aeLQskxIq/748c4FveOzbeOrtumeEfkzMvLFHEcqCUex/qp0S'
WHERE email = 'mateus.souza@motoconnect.com';

UPDATE tb_usuario
SET senha = '$2a$10$jnSSkEepGKy69Ydn5mZUhOEJ.bhhHVaJesxcTxQ058nIU4tF3OJba'
WHERE email = 'cauan.passos@motoconnect.com';


---- RFIDs
INSERT INTO tb_rfid (nome_area, latitude, longitude)
VALUES ('Área A', -23.5505, -46.6333);

INSERT INTO tb_rfid (nome_area, latitude, longitude)
VALUES ('Área B', -22.9068, -43.1729);

-- MOTOS
INSERT INTO tb_moto (modelo, placa, data_cadastro, status, tb_rfid, usuario_id)
VALUES ('SPORT', 'ABC1234', '2023-01-15', 'MANUTENCAO', 1, 1);

INSERT INTO tb_moto (modelo, placa, data_cadastro, status, tb_rfid, usuario_id)
VALUES ('POP', 'DEF5678', '2023-02-20', 'REVISADA', 2, 2);


-- HISTORICOS
INSERT INTO tb_historico_moto (parte, descricao, moto_id)
VALUES ('Motor', 'Manutenção realizada no motor.', 1);

INSERT INTO tb_historico_moto (parte, descricao, moto_id)
VALUES ('Freio', 'Troca de pastilhas de freio.', 2);


UPDATE tb_historico_moto
SET usuario_id = 1
WHERE id = 1;

UPDATE tb_historico_moto
SET usuario_id = 2
WHERE id = 2;



