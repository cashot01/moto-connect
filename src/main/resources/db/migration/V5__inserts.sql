-- USUARIOS
INSERT INTO tb_usuario (nome, email, senha, role) VALUES ('Cauan Passos', 'cauan.passos@motoconnect.com', 'admin1234', 'ADMIN');
INSERT INTO tb_usuario (nome, email, senha, role) VALUES ('Mateus Souza', 'mateus.souza@motoconnect.com', 'senha123', 'ADMIN');
INSERT INTO tb_usuario (nome, email, senha, role) VALUES ('Lucas Fialho', 'lucas.fialho@motoconnect.com', 'password987', 'ADMIN');
INSERT INTO tb_usuario (nome, email, senha, role) VALUES ('Arthur Bento', 'arthur.bento@motoconnect.com', 'bento2025', 'USER');
INSERT INTO tb_usuario (nome, email, senha, role) VALUES ('Maria Clara', 'maria.clara@motoconnect.com', 'clara1235', 'USER');

UPDATE tb_usuario
SET senha = '$2a$10$jnSSkEepGKy69Ydn5mZUhOEJ.bhhHVaJesxcTxQ058nIU4tF3OJba'
WHERE email = 'cauan.passos@motoconnect.com';

UPDATE tb_usuario
SET senha = '$2a$10$gsg2aeLQskxIq/748c4FveOzbeOrtumeEfkzMvLFHEcqCUex/qp0S'
WHERE email = 'mateus.souza@motoconnect.com';

UPDATE tb_usuario
SET senha = '$2a$10$6zIke0y4wwq9fghnWmVgh.THZdLRVHf9vpzQAv/pURIz..qf9FHcq'
WHERE email = 'lucas.fialho@motoconnect.com';

UPDATE tb_usuario
SET senha = '$2a$10$VN/GChqtpiW4wdp84y2gc.hET5R5qPZ6dP7H.83KI9xFZQPoj/il6'
WHERE email = 'arthur.bento@motoconnect.com';

UPDATE tb_usuario
SET senha = '$2a$10$yhCsMcqRz27h8paKub785eLFdGNRQbrXqIDJlay0XssJX6IYi0yH2'
WHERE email = 'maria.clara@motoconnect.com';


---- RFIDs
INSERT INTO tb_rfid (nome_area, latitude, longitude) VALUES ('Área A', -23.5505, -46.6333);
INSERT INTO tb_rfid (nome_area, latitude, longitude) VALUES ('Área B', -22.9068, -43.1729);
INSERT INTO tb_rfid (nome_area, latitude, longitude) VALUES ('Área C', -19.9245, -43.9352);
INSERT INTO tb_rfid (nome_area, latitude, longitude) VALUES ('Área D', -15.7801, -47.9292);
INSERT INTO tb_rfid (nome_area, latitude, longitude) VALUES ('Área E', -30.0331, -51.2300);


-- MOTOS
INSERT INTO tb_moto (modelo, placa, data_cadastro, status, tb_rfid, usuario_id) VALUES ('SPORT', 'ABC1234', '2023-01-15', 'NAO_INICIADO', 1, 1);
INSERT INTO tb_moto (modelo, placa, data_cadastro, status, tb_rfid, usuario_id) VALUES ('E', 'DEF5678', '2023-02-18', 'MANUTENCAO', 2, 2);
INSERT INTO tb_moto (modelo, placa, data_cadastro, status, tb_rfid, usuario_id) VALUES ('POP', 'GHI9101', '2024-10-10', 'REVISADA', 3, 3);
INSERT INTO tb_moto (modelo, placa, data_cadastro, status, tb_rfid, usuario_id) VALUES ('SPORT', 'JKL1122', '2024-12-25', 'NAO_INICIADO', 4, 4);
INSERT INTO tb_moto (modelo, placa, data_cadastro, status, tb_rfid, usuario_id) VALUES ('E', 'MNO3344', '2025-07-07', 'MANUTENCAO', 5, 5);


-- HISTORICOS
INSERT INTO tb_historico_moto (parte, descricao, moto_id, usuario_id) VALUES ('Motor', 'Troca de óleo realizada.', 1, 1);
INSERT INTO tb_historico_moto (parte, descricao, moto_id, usuario_id) VALUES ('Freio', 'Manutenção nos freios.', 2, 2);
INSERT INTO tb_historico_moto (parte, descricao, moto_id, usuario_id) VALUES ('Pneu', 'Substituição dos pneus.', 3, 3);
INSERT INTO tb_historico_moto (parte, descricao, moto_id, usuario_id) VALUES ('Suspensão', 'Revisão da suspensão.', 4, 4);
INSERT INTO tb_historico_moto (parte, descricao, moto_id, usuario_id) VALUES ('Farol', 'Troca da lâmpada do farol.', 5, 5);






