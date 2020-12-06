INSERT INTO `dictionary_project`.`user` (`username`, `email`, `password`) VALUES ('geor', 'geor@gmail.com', '123');
INSERT INTO `dictionary_project`.`user` (`username`, `email`, `password`) VALUES ('lakras', 'lakras@gmail.com', '456');

INSERT INTO `dictionary_project`.`dictionary` (`language`, `user_id`) VALUES ('English', '1');
INSERT INTO `dictionary_project`.`dictionary` (`language`, `user_id`) VALUES ('German', '1');
INSERT INTO `dictionary_project`.`dictionary` (`language`, `user_id`) VALUES ('English', '2');

INSERT INTO `dictionary_project`.`word` (`original`, `dictionary_id`) VALUES ('leverage', '1');
INSERT INTO `dictionary_project`.`word` (`original`, `dictionary_id`) VALUES ('delegate', '1');
INSERT INTO `dictionary_project`.`word` (`original`, `dictionary_id`) VALUES ('render', '3');
INSERT INTO `dictionary_project`.`word` (`original`, `dictionary_id`) VALUES ('servlet', '3');
INSERT INTO `dictionary_project`.`word` (`original`, `dictionary_id`) VALUES ('Apfel', '2');
INSERT INTO `dictionary_project`.`word` (`original`, `dictionary_id`) VALUES ('Wasser', '2');

INSERT INTO `dictionary_project`.`meaning` (`definition`, `example`, `word_id`, `type`) VALUES ('μόχλευση', 'my spade hit something solid that wouldn\'t respond to leverage', '1', '0');
INSERT INTO `dictionary_project`.`meaning` (`definition`, `example`, `word_id`, `type`) VALUES ('χρησιμοποιώ για μέγιστο όφελος', 'the organization needs to leverage its key resources', '1', '1');
INSERT INTO `dictionary_project`.`meaning` (`definition`, `example`, `word_id`, `type`) VALUES ('αντιπρόσωπος', 'congress delegates rejected the proposals', '2', '0');
INSERT INTO `dictionary_project`.`meaning` (`definition`, `example`, `word_id`, `type`) VALUES ('αναθέτω', 'the power delegated to him must never be misused', '2', '1');
INSERT INTO `dictionary_project`.`meaning` (`definition`, `example`, `word_id`, `type`) VALUES ('προσφέρω, ανταποδίδω', 'money serves as a reward for services rendered', '3', '1');
INSERT INTO `dictionary_project`.`meaning` (`definition`, `example`, `word_id`, `type`) VALUES ('καθιστώ', 'the rains rendered his escape impossible', '3', '1');
INSERT INTO `dictionary_project`.`meaning` (`definition`, `example`, `word_id`, `type`) VALUES ('μικρό πρόγραμμα που εκτελείται στον server', 'students will build servlets that generate web pages and communicate with other Java servers', '4', '0');
INSERT INTO `dictionary_project`.`meaning` (`definition`, `example`, `word_id`, `type`) VALUES ('μήλο', 'ein grüner, saurer, wurmstichiger, rotbäckiger, gebratener Apfel', '5', '0');
INSERT INTO `dictionary_project`.`meaning` (`definition`, `example`, `word_id`, `type`) VALUES ('νερό', 'auflaufendes, ablaufendes Wasser', '6', '0');
