
    create table adresse (
        id_adresse bigint not null auto_increment,
        ville varchar(50) not null,
        rue varchar(100) not null,
        code_postal varchar(255) not null,
        primary key (id_adresse)
    ) engine=InnoDB;

    create table article (
        prix_article float(23),
        id_article bigint not null auto_increment,
        nom_article varchar(100) not null,
        type_article enum ('BOISSON','DESSERT','SNACK') not null,
        primary key (id_article)
    ) engine=InnoDB;

    create table article_promotion (
        id_article bigint not null,
        id_promotion bigint not null
    ) engine=InnoDB;

    create table carte_fidelite (
        date_creation date not null,
        points_accumules integer,
        id_carte_fidelite bigint not null auto_increment,
        primary key (id_carte_fidelite)
    ) engine=InnoDB;

    create table client (
        date_naissance date not null,
        id_adresse bigint,
        id_carte_fidelite bigint,
        id_client bigint not null auto_increment,
        nom varchar(50) not null,
        prenom varchar(50) not null,
        primary key (id_client)
    ) engine=InnoDB;

    create table commande (
        date_commande date not null,
        total_commande float(23),
        id_client bigint,
        id_commande bigint not null auto_increment,
        status_commande enum ('ANNULEE','EN_COURS','LIVREE') not null,
        primary key (id_commande)
    ) engine=InnoDB;

    create table detail_commande (
        quantite_article integer,
        sous_total_detail_article float(23),
        sous_total_detail_article_apres_promo float(23),
        id_article bigint not null,
        id_commande bigint not null,
        id_detail_commande bigint not null auto_increment,
        primary key (id_detail_commande)
    ) engine=InnoDB;

    create table promotion (
        date_debut_promo date not null,
        date_fin_promo date not null,
        pourcentage_promo float(23),
        id_promotion bigint not null auto_increment,
        primary key (id_promotion)
    ) engine=InnoDB;

    alter table client 
       add constraint UKmhe9ac6rtmg8wrt1f2bfoqpmd unique (id_adresse);

    alter table client 
       add constraint UK9ablpwejn7pvmhi21uc56cnwe unique (id_carte_fidelite);

    alter table article_promotion 
       add constraint FKgh5tfvgdcl8gv19mnwmvdqibo 
       foreign key (id_promotion) 
       references promotion (id_promotion);

    alter table article_promotion 
       add constraint FK4gbllmrhovoyiqtt6dxb13ghm 
       foreign key (id_article) 
       references article (id_article);

    alter table client 
       add constraint FKpxwh4xi98rmanmlb1h88cohl9 
       foreign key (id_adresse) 
       references adresse (id_adresse);

    alter table client 
       add constraint FKmbfwlimo770j1rrxv4ddcso6r 
       foreign key (id_carte_fidelite) 
       references carte_fidelite (id_carte_fidelite);

    alter table commande 
       add constraint FKfh667737scbwrhtkephs23hq 
       foreign key (id_client) 
       references client (id_client);

    alter table detail_commande 
       add constraint FK3t348on52ab82oe4qa2nm7m4v 
       foreign key (id_article) 
       references article (id_article);

    alter table detail_commande 
       add constraint FKak2cq6il1c6q8mtgj1jh03jhh 
       foreign key (id_commande) 
       references commande (id_commande);

    create table adresse (
        id_adresse bigint not null auto_increment,
        ville varchar(50) not null,
        rue varchar(100) not null,
        code_postal varchar(255) not null,
        primary key (id_adresse)
    ) engine=InnoDB;

    create table article (
        prix_article float(23),
        id_article bigint not null auto_increment,
        nom_article varchar(100) not null,
        type_article enum ('BOISSON','DESSERT','SNACK') not null,
        primary key (id_article)
    ) engine=InnoDB;

    create table article_promotion (
        id_article bigint not null,
        id_promotion bigint not null
    ) engine=InnoDB;

    create table carte_fidelite (
        date_creation date not null,
        points_accumules integer,
        id_carte_fidelite bigint not null auto_increment,
        primary key (id_carte_fidelite)
    ) engine=InnoDB;

    create table client (
        date_naissance date not null,
        id_adresse bigint,
        id_carte_fidelite bigint,
        id_client bigint not null auto_increment,
        nom varchar(50) not null,
        prenom varchar(50) not null,
        primary key (id_client)
    ) engine=InnoDB;

    create table commande (
        date_commande date not null,
        total_commande float(23),
        id_client bigint,
        id_commande bigint not null auto_increment,
        status_commande enum ('ANNULEE','EN_COURS','LIVREE') not null,
        primary key (id_commande)
    ) engine=InnoDB;

    create table detail_commande (
        quantite_article integer,
        sous_total_detail_article float(23),
        sous_total_detail_article_apres_promo float(23),
        id_article bigint not null,
        id_commande bigint not null,
        id_detail_commande bigint not null auto_increment,
        primary key (id_detail_commande)
    ) engine=InnoDB;

    create table promotion (
        date_debut_promo date not null,
        date_fin_promo date not null,
        pourcentage_promo float(23),
        id_promotion bigint not null auto_increment,
        primary key (id_promotion)
    ) engine=InnoDB;

    alter table client 
       add constraint UKmhe9ac6rtmg8wrt1f2bfoqpmd unique (id_adresse);

    alter table client 
       add constraint UK9ablpwejn7pvmhi21uc56cnwe unique (id_carte_fidelite);

    alter table article_promotion 
       add constraint FKgh5tfvgdcl8gv19mnwmvdqibo 
       foreign key (id_promotion) 
       references promotion (id_promotion);

    alter table article_promotion 
       add constraint FK4gbllmrhovoyiqtt6dxb13ghm 
       foreign key (id_article) 
       references article (id_article);

    alter table client 
       add constraint FKpxwh4xi98rmanmlb1h88cohl9 
       foreign key (id_adresse) 
       references adresse (id_adresse);

    alter table client 
       add constraint FKmbfwlimo770j1rrxv4ddcso6r 
       foreign key (id_carte_fidelite) 
       references carte_fidelite (id_carte_fidelite);

    alter table commande 
       add constraint FKfh667737scbwrhtkephs23hq 
       foreign key (id_client) 
       references client (id_client);

    alter table detail_commande 
       add constraint FK3t348on52ab82oe4qa2nm7m4v 
       foreign key (id_article) 
       references article (id_article);

    alter table detail_commande 
       add constraint FKak2cq6il1c6q8mtgj1jh03jhh 
       foreign key (id_commande) 
       references commande (id_commande);

    create table adresse (
        id_adresse bigint not null auto_increment,
        ville varchar(50) not null,
        rue varchar(100) not null,
        code_postal varchar(255) not null,
        primary key (id_adresse)
    ) engine=InnoDB;

    create table article (
        prix_article float(23),
        id_article bigint not null auto_increment,
        nom_article varchar(100) not null,
        type_article enum ('BOISSON','DESSERT','SNACK') not null,
        primary key (id_article)
    ) engine=InnoDB;

    create table article_promotion (
        id_article bigint not null,
        id_promotion bigint not null
    ) engine=InnoDB;

    create table carte_fidelite (
        date_creation date not null,
        points_accumules integer,
        id_carte_fidelite bigint not null auto_increment,
        primary key (id_carte_fidelite)
    ) engine=InnoDB;

    create table client (
        date_naissance date not null,
        id_adresse bigint,
        id_carte_fidelite bigint,
        id_client bigint not null auto_increment,
        nom varchar(50) not null,
        prenom varchar(50) not null,
        primary key (id_client)
    ) engine=InnoDB;

    create table commande (
        date_commande date not null,
        total_commande float(23),
        id_client bigint,
        id_commande bigint not null auto_increment,
        status_commande enum ('ANNULEE','EN_COURS','LIVREE') not null,
        primary key (id_commande)
    ) engine=InnoDB;

    create table detail_commande (
        quantite_article integer,
        sous_total_detail_article float(23),
        sous_total_detail_article_apres_promo float(23),
        id_article bigint not null,
        id_commande bigint not null,
        id_detail_commande bigint not null auto_increment,
        primary key (id_detail_commande)
    ) engine=InnoDB;

    create table promotion (
        date_debut_promo date not null,
        date_fin_promo date not null,
        pourcentage_promo float(23),
        id_promotion bigint not null auto_increment,
        primary key (id_promotion)
    ) engine=InnoDB;

    alter table client 
       add constraint UKmhe9ac6rtmg8wrt1f2bfoqpmd unique (id_adresse);

    alter table client 
       add constraint UK9ablpwejn7pvmhi21uc56cnwe unique (id_carte_fidelite);

    alter table article_promotion 
       add constraint FKgh5tfvgdcl8gv19mnwmvdqibo 
       foreign key (id_promotion) 
       references promotion (id_promotion);

    alter table article_promotion 
       add constraint FK4gbllmrhovoyiqtt6dxb13ghm 
       foreign key (id_article) 
       references article (id_article);

    alter table client 
       add constraint FKpxwh4xi98rmanmlb1h88cohl9 
       foreign key (id_adresse) 
       references adresse (id_adresse);

    alter table client 
       add constraint FKmbfwlimo770j1rrxv4ddcso6r 
       foreign key (id_carte_fidelite) 
       references carte_fidelite (id_carte_fidelite);

    alter table commande 
       add constraint FKfh667737scbwrhtkephs23hq 
       foreign key (id_client) 
       references client (id_client);

    alter table detail_commande 
       add constraint FK3t348on52ab82oe4qa2nm7m4v 
       foreign key (id_article) 
       references article (id_article);

    alter table detail_commande 
       add constraint FKak2cq6il1c6q8mtgj1jh03jhh 
       foreign key (id_commande) 
       references commande (id_commande);

    create table adresse (
        id_adresse bigint not null auto_increment,
        ville varchar(50) not null,
        rue varchar(100) not null,
        code_postal varchar(255) not null,
        primary key (id_adresse)
    ) engine=InnoDB;

    create table article (
        prix_article float(23),
        id_article bigint not null auto_increment,
        nom_article varchar(100) not null,
        type_article enum ('BOISSON','DESSERT','SNACK') not null,
        primary key (id_article)
    ) engine=InnoDB;

    create table article_promotion (
        id_article bigint not null,
        id_promotion bigint not null
    ) engine=InnoDB;

    create table carte_fidelite (
        date_creation date not null,
        points_accumules integer,
        id_carte_fidelite bigint not null auto_increment,
        primary key (id_carte_fidelite)
    ) engine=InnoDB;

    create table client (
        date_naissance date not null,
        id_adresse bigint,
        id_carte_fidelite bigint,
        id_client bigint not null auto_increment,
        nom varchar(50) not null,
        prenom varchar(50) not null,
        primary key (id_client)
    ) engine=InnoDB;

    create table commande (
        date_commande date not null,
        total_commande float(23),
        id_client bigint,
        id_commande bigint not null auto_increment,
        status_commande enum ('ANNULEE','EN_COURS','LIVREE') not null,
        primary key (id_commande)
    ) engine=InnoDB;

    create table detail_commande (
        quantite_article integer,
        sous_total_detail_article float(23),
        sous_total_detail_article_apres_promo float(23),
        id_article bigint not null,
        id_commande bigint not null,
        id_detail_commande bigint not null auto_increment,
        primary key (id_detail_commande)
    ) engine=InnoDB;

    create table promotion (
        date_debut_promo date not null,
        date_fin_promo date not null,
        pourcentage_promo float(23),
        id_promotion bigint not null auto_increment,
        primary key (id_promotion)
    ) engine=InnoDB;

    alter table client 
       add constraint UKmhe9ac6rtmg8wrt1f2bfoqpmd unique (id_adresse);

    alter table client 
       add constraint UK9ablpwejn7pvmhi21uc56cnwe unique (id_carte_fidelite);

    alter table article_promotion 
       add constraint FKgh5tfvgdcl8gv19mnwmvdqibo 
       foreign key (id_promotion) 
       references promotion (id_promotion);

    alter table article_promotion 
       add constraint FK4gbllmrhovoyiqtt6dxb13ghm 
       foreign key (id_article) 
       references article (id_article);

    alter table client 
       add constraint FKpxwh4xi98rmanmlb1h88cohl9 
       foreign key (id_adresse) 
       references adresse (id_adresse);

    alter table client 
       add constraint FKmbfwlimo770j1rrxv4ddcso6r 
       foreign key (id_carte_fidelite) 
       references carte_fidelite (id_carte_fidelite);

    alter table commande 
       add constraint FKfh667737scbwrhtkephs23hq 
       foreign key (id_client) 
       references client (id_client);

    alter table detail_commande 
       add constraint FK3t348on52ab82oe4qa2nm7m4v 
       foreign key (id_article) 
       references article (id_article);

    alter table detail_commande 
       add constraint FKak2cq6il1c6q8mtgj1jh03jhh 
       foreign key (id_commande) 
       references commande (id_commande);

    create table adresse (
        id_adresse bigint not null auto_increment,
        ville varchar(50) not null,
        rue varchar(100) not null,
        code_postal varchar(255) not null,
        primary key (id_adresse)
    ) engine=InnoDB;

    create table article (
        prix_article float(23),
        id_article bigint not null auto_increment,
        nom_article varchar(100) not null,
        type_article enum ('BOISSON','DESSERT','SNACK') not null,
        primary key (id_article)
    ) engine=InnoDB;

    create table article_promotion (
        id_article bigint not null,
        id_promotion bigint not null
    ) engine=InnoDB;

    create table carte_fidelite (
        date_creation date not null,
        points_accumules integer,
        id_carte_fidelite bigint not null auto_increment,
        primary key (id_carte_fidelite)
    ) engine=InnoDB;

    create table client (
        date_naissance date not null,
        id_adresse bigint,
        id_carte_fidelite bigint,
        id_client bigint not null auto_increment,
        nom varchar(50) not null,
        prenom varchar(50) not null,
        primary key (id_client)
    ) engine=InnoDB;

    create table commande (
        date_commande date not null,
        total_commande float(23),
        id_client bigint,
        id_commande bigint not null auto_increment,
        status_commande enum ('ANNULEE','EN_COURS','LIVREE') not null,
        primary key (id_commande)
    ) engine=InnoDB;

    create table detail_commande (
        quantite_article integer,
        sous_total_detail_article float(23),
        sous_total_detail_article_apres_promo float(23),
        id_article bigint not null,
        id_commande bigint not null,
        id_detail_commande bigint not null auto_increment,
        primary key (id_detail_commande)
    ) engine=InnoDB;

    create table promotion (
        date_debut_promo date not null,
        date_fin_promo date not null,
        pourcentage_promo float(23),
        id_promotion bigint not null auto_increment,
        primary key (id_promotion)
    ) engine=InnoDB;

    alter table client 
       add constraint UKmhe9ac6rtmg8wrt1f2bfoqpmd unique (id_adresse);

    alter table client 
       add constraint UK9ablpwejn7pvmhi21uc56cnwe unique (id_carte_fidelite);

    alter table article_promotion 
       add constraint FKgh5tfvgdcl8gv19mnwmvdqibo 
       foreign key (id_promotion) 
       references promotion (id_promotion);

    alter table article_promotion 
       add constraint FK4gbllmrhovoyiqtt6dxb13ghm 
       foreign key (id_article) 
       references article (id_article);

    alter table client 
       add constraint FKpxwh4xi98rmanmlb1h88cohl9 
       foreign key (id_adresse) 
       references adresse (id_adresse);

    alter table client 
       add constraint FKmbfwlimo770j1rrxv4ddcso6r 
       foreign key (id_carte_fidelite) 
       references carte_fidelite (id_carte_fidelite);

    alter table commande 
       add constraint FKfh667737scbwrhtkephs23hq 
       foreign key (id_client) 
       references client (id_client);

    alter table detail_commande 
       add constraint FK3t348on52ab82oe4qa2nm7m4v 
       foreign key (id_article) 
       references article (id_article);

    alter table detail_commande 
       add constraint FKak2cq6il1c6q8mtgj1jh03jhh 
       foreign key (id_commande) 
       references commande (id_commande);

    create table adresse (
        id_adresse bigint not null auto_increment,
        ville varchar(50) not null,
        rue varchar(100) not null,
        code_postal varchar(255) not null,
        primary key (id_adresse)
    ) engine=InnoDB;

    create table article (
        prix_article float(23),
        id_article bigint not null auto_increment,
        nom_article varchar(100) not null,
        type_article enum ('BOISSON','DESSERT','SNACK') not null,
        primary key (id_article)
    ) engine=InnoDB;

    create table article_promotion (
        id_article bigint not null,
        id_promotion bigint not null
    ) engine=InnoDB;

    create table carte_fidelite (
        date_creation date not null,
        points_accumules integer,
        id_carte_fidelite bigint not null auto_increment,
        primary key (id_carte_fidelite)
    ) engine=InnoDB;

    create table client (
        date_naissance date not null,
        id_adresse bigint,
        id_carte_fidelite bigint,
        id_client bigint not null auto_increment,
        nom varchar(50) not null,
        prenom varchar(50) not null,
        primary key (id_client)
    ) engine=InnoDB;

    create table commande (
        date_commande date not null,
        total_commande float(23),
        id_client bigint,
        id_commande bigint not null auto_increment,
        status_commande enum ('ANNULEE','EN_COURS','LIVREE') not null,
        primary key (id_commande)
    ) engine=InnoDB;

    create table detail_commande (
        quantite_article integer,
        sous_total_detail_article float(23),
        sous_total_detail_article_apres_promo float(23),
        id_article bigint not null,
        id_commande bigint not null,
        id_detail_commande bigint not null auto_increment,
        primary key (id_detail_commande)
    ) engine=InnoDB;

    create table promotion (
        date_debut_promo date not null,
        date_fin_promo date not null,
        pourcentage_promo float(23),
        id_promotion bigint not null auto_increment,
        primary key (id_promotion)
    ) engine=InnoDB;

    alter table client 
       add constraint UKmhe9ac6rtmg8wrt1f2bfoqpmd unique (id_adresse);

    alter table client 
       add constraint UK9ablpwejn7pvmhi21uc56cnwe unique (id_carte_fidelite);

    alter table article_promotion 
       add constraint FKgh5tfvgdcl8gv19mnwmvdqibo 
       foreign key (id_promotion) 
       references promotion (id_promotion);

    alter table article_promotion 
       add constraint FK4gbllmrhovoyiqtt6dxb13ghm 
       foreign key (id_article) 
       references article (id_article);

    alter table client 
       add constraint FKpxwh4xi98rmanmlb1h88cohl9 
       foreign key (id_adresse) 
       references adresse (id_adresse);

    alter table client 
       add constraint FKmbfwlimo770j1rrxv4ddcso6r 
       foreign key (id_carte_fidelite) 
       references carte_fidelite (id_carte_fidelite);

    alter table commande 
       add constraint FKfh667737scbwrhtkephs23hq 
       foreign key (id_client) 
       references client (id_client);

    alter table detail_commande 
       add constraint FK3t348on52ab82oe4qa2nm7m4v 
       foreign key (id_article) 
       references article (id_article);

    alter table detail_commande 
       add constraint FKak2cq6il1c6q8mtgj1jh03jhh 
       foreign key (id_commande) 
       references commande (id_commande);

    create table adresse (
        id_adresse bigint not null auto_increment,
        ville varchar(50) not null,
        rue varchar(100) not null,
        code_postal varchar(255) not null,
        primary key (id_adresse)
    ) engine=InnoDB;

    create table article (
        prix_article float(23),
        id_article bigint not null auto_increment,
        nom_article varchar(100) not null,
        type_article enum ('BOISSON','DESSERT','SNACK') not null,
        primary key (id_article)
    ) engine=InnoDB;

    create table article_promotion (
        id_article bigint not null,
        id_promotion bigint not null
    ) engine=InnoDB;

    create table carte_fidelite (
        date_creation date not null,
        points_accumules integer,
        id_carte_fidelite bigint not null auto_increment,
        primary key (id_carte_fidelite)
    ) engine=InnoDB;

    create table client (
        date_naissance date not null,
        id_adresse bigint,
        id_carte_fidelite bigint,
        id_client bigint not null auto_increment,
        nom varchar(50) not null,
        prenom varchar(50) not null,
        primary key (id_client)
    ) engine=InnoDB;

    create table commande (
        date_commande date not null,
        total_commande float(23),
        id_client bigint,
        id_commande bigint not null auto_increment,
        status_commande enum ('ANNULEE','EN_COURS','LIVREE') not null,
        primary key (id_commande)
    ) engine=InnoDB;

    create table detail_commande (
        quantite_article integer,
        sous_total_detail_article float(23),
        sous_total_detail_article_apres_promo float(23),
        id_article bigint not null,
        id_commande bigint not null,
        id_detail_commande bigint not null auto_increment,
        primary key (id_detail_commande)
    ) engine=InnoDB;

    create table promotion (
        date_debut_promo date not null,
        date_fin_promo date not null,
        pourcentage_promo float(23),
        id_promotion bigint not null auto_increment,
        primary key (id_promotion)
    ) engine=InnoDB;

    alter table client 
       add constraint UKmhe9ac6rtmg8wrt1f2bfoqpmd unique (id_adresse);

    alter table client 
       add constraint UK9ablpwejn7pvmhi21uc56cnwe unique (id_carte_fidelite);

    alter table article_promotion 
       add constraint FKgh5tfvgdcl8gv19mnwmvdqibo 
       foreign key (id_promotion) 
       references promotion (id_promotion);

    alter table article_promotion 
       add constraint FK4gbllmrhovoyiqtt6dxb13ghm 
       foreign key (id_article) 
       references article (id_article);

    alter table client 
       add constraint FKpxwh4xi98rmanmlb1h88cohl9 
       foreign key (id_adresse) 
       references adresse (id_adresse);

    alter table client 
       add constraint FKmbfwlimo770j1rrxv4ddcso6r 
       foreign key (id_carte_fidelite) 
       references carte_fidelite (id_carte_fidelite);

    alter table commande 
       add constraint FKfh667737scbwrhtkephs23hq 
       foreign key (id_client) 
       references client (id_client);

    alter table detail_commande 
       add constraint FK3t348on52ab82oe4qa2nm7m4v 
       foreign key (id_article) 
       references article (id_article);

    alter table detail_commande 
       add constraint FKak2cq6il1c6q8mtgj1jh03jhh 
       foreign key (id_commande) 
       references commande (id_commande);

    create table adresse (
        id_adresse bigint not null auto_increment,
        ville varchar(50) not null,
        rue varchar(100) not null,
        code_postal varchar(255) not null,
        primary key (id_adresse)
    ) engine=InnoDB;

    create table article (
        prix_article float(23),
        id_article bigint not null auto_increment,
        nom_article varchar(100) not null,
        type_article enum ('BOISSON','DESSERT','SNACK') not null,
        primary key (id_article)
    ) engine=InnoDB;

    create table article_promotion (
        id_article bigint not null,
        id_promotion bigint not null
    ) engine=InnoDB;

    create table carte_fidelite (
        date_creation date not null,
        points_accumules integer,
        id_carte_fidelite bigint not null auto_increment,
        primary key (id_carte_fidelite)
    ) engine=InnoDB;

    create table client (
        date_naissance date not null,
        id_adresse bigint,
        id_carte_fidelite bigint,
        id_client bigint not null auto_increment,
        nom varchar(50) not null,
        prenom varchar(50) not null,
        primary key (id_client)
    ) engine=InnoDB;

    create table commande (
        date_commande date not null,
        total_commande float(23),
        id_client bigint,
        id_commande bigint not null auto_increment,
        status_commande enum ('ANNULEE','EN_COURS','LIVREE') not null,
        primary key (id_commande)
    ) engine=InnoDB;

    create table detail_commande (
        quantite_article integer,
        sous_total_detail_article float(23),
        sous_total_detail_article_apres_promo float(23),
        id_article bigint not null,
        id_commande bigint not null,
        id_detail_commande bigint not null auto_increment,
        primary key (id_detail_commande)
    ) engine=InnoDB;

    create table promotion (
        date_debut_promo date not null,
        date_fin_promo date not null,
        pourcentage_promo float(23),
        id_promotion bigint not null auto_increment,
        primary key (id_promotion)
    ) engine=InnoDB;

    alter table client 
       add constraint UKmhe9ac6rtmg8wrt1f2bfoqpmd unique (id_adresse);

    alter table client 
       add constraint UK9ablpwejn7pvmhi21uc56cnwe unique (id_carte_fidelite);

    alter table article_promotion 
       add constraint FKgh5tfvgdcl8gv19mnwmvdqibo 
       foreign key (id_promotion) 
       references promotion (id_promotion);

    alter table article_promotion 
       add constraint FK4gbllmrhovoyiqtt6dxb13ghm 
       foreign key (id_article) 
       references article (id_article);

    alter table client 
       add constraint FKpxwh4xi98rmanmlb1h88cohl9 
       foreign key (id_adresse) 
       references adresse (id_adresse);

    alter table client 
       add constraint FKmbfwlimo770j1rrxv4ddcso6r 
       foreign key (id_carte_fidelite) 
       references carte_fidelite (id_carte_fidelite);

    alter table commande 
       add constraint FKfh667737scbwrhtkephs23hq 
       foreign key (id_client) 
       references client (id_client);

    alter table detail_commande 
       add constraint FK3t348on52ab82oe4qa2nm7m4v 
       foreign key (id_article) 
       references article (id_article);

    alter table detail_commande 
       add constraint FKak2cq6il1c6q8mtgj1jh03jhh 
       foreign key (id_commande) 
       references commande (id_commande);

    create table adresse (
        id_adresse bigint not null auto_increment,
        ville varchar(50) not null,
        rue varchar(100) not null,
        code_postal varchar(255) not null,
        primary key (id_adresse)
    ) engine=InnoDB;

    create table article (
        prix_article float(23),
        id_article bigint not null auto_increment,
        nom_article varchar(100) not null,
        type_article enum ('BOISSON','DESSERT','SNACK') not null,
        primary key (id_article)
    ) engine=InnoDB;

    create table article_promotion (
        id_article bigint not null,
        id_promotion bigint not null
    ) engine=InnoDB;

    create table carte_fidelite (
        date_creation date not null,
        points_accumules integer,
        id_carte_fidelite bigint not null auto_increment,
        primary key (id_carte_fidelite)
    ) engine=InnoDB;

    create table client (
        date_naissance date not null,
        id_adresse bigint,
        id_carte_fidelite bigint,
        id_client bigint not null auto_increment,
        nom varchar(50) not null,
        prenom varchar(50) not null,
        primary key (id_client)
    ) engine=InnoDB;

    create table commande (
        date_commande date not null,
        total_commande float(23),
        id_client bigint,
        id_commande bigint not null auto_increment,
        status_commande enum ('ANNULEE','EN_COURS','LIVREE') not null,
        primary key (id_commande)
    ) engine=InnoDB;

    create table detail_commande (
        quantite_article integer,
        sous_total_detail_article float(23),
        sous_total_detail_article_apres_promo float(23),
        id_article bigint not null,
        id_commande bigint not null,
        id_detail_commande bigint not null auto_increment,
        primary key (id_detail_commande)
    ) engine=InnoDB;

    create table promotion (
        date_debut_promo date not null,
        date_fin_promo date not null,
        pourcentage_promo float(23),
        id_promotion bigint not null auto_increment,
        primary key (id_promotion)
    ) engine=InnoDB;

    alter table client 
       add constraint UKmhe9ac6rtmg8wrt1f2bfoqpmd unique (id_adresse);

    alter table client 
       add constraint UK9ablpwejn7pvmhi21uc56cnwe unique (id_carte_fidelite);

    alter table article_promotion 
       add constraint FKgh5tfvgdcl8gv19mnwmvdqibo 
       foreign key (id_promotion) 
       references promotion (id_promotion);

    alter table article_promotion 
       add constraint FK4gbllmrhovoyiqtt6dxb13ghm 
       foreign key (id_article) 
       references article (id_article);

    alter table client 
       add constraint FKpxwh4xi98rmanmlb1h88cohl9 
       foreign key (id_adresse) 
       references adresse (id_adresse);

    alter table client 
       add constraint FKmbfwlimo770j1rrxv4ddcso6r 
       foreign key (id_carte_fidelite) 
       references carte_fidelite (id_carte_fidelite);

    alter table commande 
       add constraint FKfh667737scbwrhtkephs23hq 
       foreign key (id_client) 
       references client (id_client);

    alter table detail_commande 
       add constraint FK3t348on52ab82oe4qa2nm7m4v 
       foreign key (id_article) 
       references article (id_article);

    alter table detail_commande 
       add constraint FKak2cq6il1c6q8mtgj1jh03jhh 
       foreign key (id_commande) 
       references commande (id_commande);

    create table adresse (
        id_adresse bigint not null auto_increment,
        ville varchar(50) not null,
        rue varchar(100) not null,
        code_postal varchar(255) not null,
        primary key (id_adresse)
    ) engine=InnoDB;

    create table article (
        prix_article float(23),
        id_article bigint not null auto_increment,
        nom_article varchar(100) not null,
        type_article enum ('BOISSON','DESSERT','SNACK') not null,
        primary key (id_article)
    ) engine=InnoDB;

    create table article_promotion (
        id_article bigint not null,
        id_promotion bigint not null
    ) engine=InnoDB;

    create table carte_fidelite (
        date_creation date not null,
        points_accumules integer,
        id_carte_fidelite bigint not null auto_increment,
        primary key (id_carte_fidelite)
    ) engine=InnoDB;

    create table client (
        date_naissance date not null,
        id_adresse bigint,
        id_carte_fidelite bigint,
        id_client bigint not null auto_increment,
        nom varchar(50) not null,
        prenom varchar(50) not null,
        primary key (id_client)
    ) engine=InnoDB;

    create table commande (
        date_commande date not null,
        total_commande float(23),
        id_client bigint,
        id_commande bigint not null auto_increment,
        status_commande enum ('ANNULEE','EN_COURS','LIVREE') not null,
        primary key (id_commande)
    ) engine=InnoDB;

    create table detail_commande (
        quantite_article integer,
        sous_total_detail_article float(23),
        sous_total_detail_article_apres_promo float(23),
        id_article bigint not null,
        id_commande bigint not null,
        id_detail_commande bigint not null auto_increment,
        primary key (id_detail_commande)
    ) engine=InnoDB;

    create table promotion (
        date_debut_promo date not null,
        date_fin_promo date not null,
        pourcentage_promo float(23),
        id_promotion bigint not null auto_increment,
        primary key (id_promotion)
    ) engine=InnoDB;

    alter table client 
       add constraint UKmhe9ac6rtmg8wrt1f2bfoqpmd unique (id_adresse);

    alter table client 
       add constraint UK9ablpwejn7pvmhi21uc56cnwe unique (id_carte_fidelite);

    alter table article_promotion 
       add constraint FKgh5tfvgdcl8gv19mnwmvdqibo 
       foreign key (id_promotion) 
       references promotion (id_promotion);

    alter table article_promotion 
       add constraint FK4gbllmrhovoyiqtt6dxb13ghm 
       foreign key (id_article) 
       references article (id_article);

    alter table client 
       add constraint FKpxwh4xi98rmanmlb1h88cohl9 
       foreign key (id_adresse) 
       references adresse (id_adresse);

    alter table client 
       add constraint FKmbfwlimo770j1rrxv4ddcso6r 
       foreign key (id_carte_fidelite) 
       references carte_fidelite (id_carte_fidelite);

    alter table commande 
       add constraint FKfh667737scbwrhtkephs23hq 
       foreign key (id_client) 
       references client (id_client);

    alter table detail_commande 
       add constraint FK3t348on52ab82oe4qa2nm7m4v 
       foreign key (id_article) 
       references article (id_article);

    alter table detail_commande 
       add constraint FKak2cq6il1c6q8mtgj1jh03jhh 
       foreign key (id_commande) 
       references commande (id_commande);

    create table adresse (
        id_adresse bigint not null auto_increment,
        ville varchar(50) not null,
        rue varchar(100) not null,
        code_postal varchar(255) not null,
        primary key (id_adresse)
    ) engine=InnoDB;

    create table article (
        prix_article float(23),
        id_article bigint not null auto_increment,
        nom_article varchar(100) not null,
        type_article enum ('BOISSON','DESSERT','SNACK') not null,
        primary key (id_article)
    ) engine=InnoDB;

    create table article_promotion (
        id_article bigint not null,
        id_promotion bigint not null
    ) engine=InnoDB;

    create table carte_fidelite (
        date_creation date not null,
        points_accumules integer,
        id_carte_fidelite bigint not null auto_increment,
        primary key (id_carte_fidelite)
    ) engine=InnoDB;

    create table client (
        date_naissance date not null,
        id_adresse bigint,
        id_carte_fidelite bigint,
        id_client bigint not null auto_increment,
        nom varchar(50) not null,
        prenom varchar(50) not null,
        primary key (id_client)
    ) engine=InnoDB;

    create table commande (
        date_commande date not null,
        total_commande float(23),
        id_client bigint,
        id_commande bigint not null auto_increment,
        status_commande enum ('ANNULEE','EN_COURS','LIVREE') not null,
        primary key (id_commande)
    ) engine=InnoDB;

    create table detail_commande (
        quantite_article integer,
        sous_total_detail_article float(23),
        sous_total_detail_article_apres_promo float(23),
        id_article bigint not null,
        id_commande bigint not null,
        id_detail_commande bigint not null auto_increment,
        primary key (id_detail_commande)
    ) engine=InnoDB;

    create table promotion (
        date_debut_promo date not null,
        date_fin_promo date not null,
        pourcentage_promo float(23),
        id_promotion bigint not null auto_increment,
        primary key (id_promotion)
    ) engine=InnoDB;

    alter table client 
       add constraint UKmhe9ac6rtmg8wrt1f2bfoqpmd unique (id_adresse);

    alter table client 
       add constraint UK9ablpwejn7pvmhi21uc56cnwe unique (id_carte_fidelite);

    alter table article_promotion 
       add constraint FKgh5tfvgdcl8gv19mnwmvdqibo 
       foreign key (id_promotion) 
       references promotion (id_promotion);

    alter table article_promotion 
       add constraint FK4gbllmrhovoyiqtt6dxb13ghm 
       foreign key (id_article) 
       references article (id_article);

    alter table client 
       add constraint FKpxwh4xi98rmanmlb1h88cohl9 
       foreign key (id_adresse) 
       references adresse (id_adresse);

    alter table client 
       add constraint FKmbfwlimo770j1rrxv4ddcso6r 
       foreign key (id_carte_fidelite) 
       references carte_fidelite (id_carte_fidelite);

    alter table commande 
       add constraint FKfh667737scbwrhtkephs23hq 
       foreign key (id_client) 
       references client (id_client);

    alter table detail_commande 
       add constraint FK3t348on52ab82oe4qa2nm7m4v 
       foreign key (id_article) 
       references article (id_article);

    alter table detail_commande 
       add constraint FKak2cq6il1c6q8mtgj1jh03jhh 
       foreign key (id_commande) 
       references commande (id_commande);

    create table adresse (
        id_adresse bigint not null auto_increment,
        ville varchar(50) not null,
        rue varchar(100) not null,
        code_postal varchar(255) not null,
        primary key (id_adresse)
    ) engine=InnoDB;

    create table article (
        prix_article float(23),
        id_article bigint not null auto_increment,
        nom_article varchar(100) not null,
        type_article enum ('BOISSON','DESSERT','SNACK') not null,
        primary key (id_article)
    ) engine=InnoDB;

    create table article_promotion (
        id_article bigint not null,
        id_promotion bigint not null
    ) engine=InnoDB;

    create table carte_fidelite (
        date_creation date not null,
        points_accumules integer,
        id_carte_fidelite bigint not null auto_increment,
        primary key (id_carte_fidelite)
    ) engine=InnoDB;

    create table client (
        date_naissance date not null,
        id_adresse bigint,
        id_carte_fidelite bigint,
        id_client bigint not null auto_increment,
        nom varchar(50) not null,
        prenom varchar(50) not null,
        primary key (id_client)
    ) engine=InnoDB;

    create table commande (
        date_commande date not null,
        total_commande float(23),
        id_client bigint,
        id_commande bigint not null auto_increment,
        status_commande enum ('ANNULEE','EN_COURS','LIVREE') not null,
        primary key (id_commande)
    ) engine=InnoDB;

    create table detail_commande (
        quantite_article integer,
        sous_total_detail_article float(23),
        sous_total_detail_article_apres_promo float(23),
        id_article bigint not null,
        id_commande bigint not null,
        id_detail_commande bigint not null auto_increment,
        primary key (id_detail_commande)
    ) engine=InnoDB;

    create table promotion (
        date_debut_promo date not null,
        date_fin_promo date not null,
        pourcentage_promo float(23),
        id_promotion bigint not null auto_increment,
        primary key (id_promotion)
    ) engine=InnoDB;

    alter table client 
       add constraint UKmhe9ac6rtmg8wrt1f2bfoqpmd unique (id_adresse);

    alter table client 
       add constraint UK9ablpwejn7pvmhi21uc56cnwe unique (id_carte_fidelite);

    alter table article_promotion 
       add constraint FKgh5tfvgdcl8gv19mnwmvdqibo 
       foreign key (id_promotion) 
       references promotion (id_promotion);

    alter table article_promotion 
       add constraint FK4gbllmrhovoyiqtt6dxb13ghm 
       foreign key (id_article) 
       references article (id_article);

    alter table client 
       add constraint FKpxwh4xi98rmanmlb1h88cohl9 
       foreign key (id_adresse) 
       references adresse (id_adresse);

    alter table client 
       add constraint FKmbfwlimo770j1rrxv4ddcso6r 
       foreign key (id_carte_fidelite) 
       references carte_fidelite (id_carte_fidelite);

    alter table commande 
       add constraint FKfh667737scbwrhtkephs23hq 
       foreign key (id_client) 
       references client (id_client);

    alter table detail_commande 
       add constraint FK3t348on52ab82oe4qa2nm7m4v 
       foreign key (id_article) 
       references article (id_article);

    alter table detail_commande 
       add constraint FKak2cq6il1c6q8mtgj1jh03jhh 
       foreign key (id_commande) 
       references commande (id_commande);

    create table adresse (
        id_adresse bigint not null auto_increment,
        ville varchar(50) not null,
        rue varchar(100) not null,
        code_postal varchar(255) not null,
        primary key (id_adresse)
    ) engine=InnoDB;

    create table article (
        prix_article float(23),
        id_article bigint not null auto_increment,
        nom_article varchar(100) not null,
        type_article enum ('BOISSON','DESSERT','SNACK') not null,
        primary key (id_article)
    ) engine=InnoDB;

    create table article_promotion (
        id_article bigint not null,
        id_promotion bigint not null
    ) engine=InnoDB;

    create table carte_fidelite (
        date_creation date not null,
        points_accumules integer,
        id_carte_fidelite bigint not null auto_increment,
        primary key (id_carte_fidelite)
    ) engine=InnoDB;

    create table client (
        date_naissance date not null,
        id_adresse bigint,
        id_carte_fidelite bigint,
        id_client bigint not null auto_increment,
        nom varchar(50) not null,
        prenom varchar(50) not null,
        primary key (id_client)
    ) engine=InnoDB;

    create table commande (
        date_commande date not null,
        total_commande float(23),
        id_client bigint,
        id_commande bigint not null auto_increment,
        status_commande enum ('ANNULEE','EN_COURS','LIVREE') not null,
        primary key (id_commande)
    ) engine=InnoDB;

    create table detail_commande (
        quantite_article integer,
        sous_total_detail_article float(23),
        sous_total_detail_article_apres_promo float(23),
        id_article bigint not null,
        id_commande bigint not null,
        id_detail_commande bigint not null auto_increment,
        primary key (id_detail_commande)
    ) engine=InnoDB;

    create table promotion (
        date_debut_promo date not null,
        date_fin_promo date not null,
        pourcentage_promo float(23),
        id_promotion bigint not null auto_increment,
        primary key (id_promotion)
    ) engine=InnoDB;

    alter table client 
       add constraint UKmhe9ac6rtmg8wrt1f2bfoqpmd unique (id_adresse);

    alter table client 
       add constraint UK9ablpwejn7pvmhi21uc56cnwe unique (id_carte_fidelite);

    alter table article_promotion 
       add constraint FKgh5tfvgdcl8gv19mnwmvdqibo 
       foreign key (id_promotion) 
       references promotion (id_promotion);

    alter table article_promotion 
       add constraint FK4gbllmrhovoyiqtt6dxb13ghm 
       foreign key (id_article) 
       references article (id_article);

    alter table client 
       add constraint FKpxwh4xi98rmanmlb1h88cohl9 
       foreign key (id_adresse) 
       references adresse (id_adresse);

    alter table client 
       add constraint FKmbfwlimo770j1rrxv4ddcso6r 
       foreign key (id_carte_fidelite) 
       references carte_fidelite (id_carte_fidelite);

    alter table commande 
       add constraint FKfh667737scbwrhtkephs23hq 
       foreign key (id_client) 
       references client (id_client);

    alter table detail_commande 
       add constraint FK3t348on52ab82oe4qa2nm7m4v 
       foreign key (id_article) 
       references article (id_article);

    alter table detail_commande 
       add constraint FKak2cq6il1c6q8mtgj1jh03jhh 
       foreign key (id_commande) 
       references commande (id_commande);

    create table adresse (
        id_adresse bigint not null auto_increment,
        ville varchar(50) not null,
        rue varchar(100) not null,
        code_postal varchar(255) not null,
        primary key (id_adresse)
    ) engine=InnoDB;

    create table article (
        prix_article float(23),
        id_article bigint not null auto_increment,
        nom_article varchar(100) not null,
        type_article enum ('BOISSON','DESSERT','SNACK') not null,
        primary key (id_article)
    ) engine=InnoDB;

    create table article_promotion (
        id_article bigint not null,
        id_promotion bigint not null
    ) engine=InnoDB;

    create table carte_fidelite (
        date_creation date not null,
        points_accumules integer,
        id_carte_fidelite bigint not null auto_increment,
        primary key (id_carte_fidelite)
    ) engine=InnoDB;

    create table client (
        date_naissance date not null,
        id_adresse bigint,
        id_carte_fidelite bigint,
        id_client bigint not null auto_increment,
        nom varchar(50) not null,
        prenom varchar(50) not null,
        primary key (id_client)
    ) engine=InnoDB;

    create table commande (
        date_commande date not null,
        total_commande float(23),
        id_client bigint,
        id_commande bigint not null auto_increment,
        status_commande enum ('ANNULEE','EN_COURS','LIVREE') not null,
        primary key (id_commande)
    ) engine=InnoDB;

    create table detail_commande (
        quantite_article integer,
        sous_total_detail_article float(23),
        sous_total_detail_article_apres_promo float(23),
        id_article bigint not null,
        id_commande bigint not null,
        id_detail_commande bigint not null auto_increment,
        primary key (id_detail_commande)
    ) engine=InnoDB;

    create table promotion (
        date_debut_promo date not null,
        date_fin_promo date not null,
        pourcentage_promo float(23),
        id_promotion bigint not null auto_increment,
        primary key (id_promotion)
    ) engine=InnoDB;

    alter table client 
       add constraint UKmhe9ac6rtmg8wrt1f2bfoqpmd unique (id_adresse);

    alter table client 
       add constraint UK9ablpwejn7pvmhi21uc56cnwe unique (id_carte_fidelite);

    alter table article_promotion 
       add constraint FKgh5tfvgdcl8gv19mnwmvdqibo 
       foreign key (id_promotion) 
       references promotion (id_promotion);

    alter table article_promotion 
       add constraint FK4gbllmrhovoyiqtt6dxb13ghm 
       foreign key (id_article) 
       references article (id_article);

    alter table client 
       add constraint FKpxwh4xi98rmanmlb1h88cohl9 
       foreign key (id_adresse) 
       references adresse (id_adresse);

    alter table client 
       add constraint FKmbfwlimo770j1rrxv4ddcso6r 
       foreign key (id_carte_fidelite) 
       references carte_fidelite (id_carte_fidelite);

    alter table commande 
       add constraint FKfh667737scbwrhtkephs23hq 
       foreign key (id_client) 
       references client (id_client);

    alter table detail_commande 
       add constraint FK3t348on52ab82oe4qa2nm7m4v 
       foreign key (id_article) 
       references article (id_article);

    alter table detail_commande 
       add constraint FKak2cq6il1c6q8mtgj1jh03jhh 
       foreign key (id_commande) 
       references commande (id_commande);

    create table adresse (
        id_adresse bigint not null auto_increment,
        ville varchar(50) not null,
        rue varchar(100) not null,
        code_postal varchar(255) not null,
        primary key (id_adresse)
    ) engine=InnoDB;

    create table article (
        prix_article float(23),
        id_article bigint not null auto_increment,
        nom_article varchar(100) not null,
        type_article enum ('BOISSON','DESSERT','SNACK') not null,
        primary key (id_article)
    ) engine=InnoDB;

    create table article_promotion (
        id_article bigint not null,
        id_promotion bigint not null
    ) engine=InnoDB;

    create table carte_fidelite (
        date_creation date not null,
        points_accumules integer,
        id_carte_fidelite bigint not null auto_increment,
        primary key (id_carte_fidelite)
    ) engine=InnoDB;

    create table client (
        date_naissance date not null,
        id_adresse bigint,
        id_carte_fidelite bigint,
        id_client bigint not null auto_increment,
        nom varchar(50) not null,
        prenom varchar(50) not null,
        primary key (id_client)
    ) engine=InnoDB;

    create table commande (
        date_commande date not null,
        total_commande float(23),
        id_client bigint,
        id_commande bigint not null auto_increment,
        status_commande enum ('ANNULEE','EN_COURS','LIVREE') not null,
        primary key (id_commande)
    ) engine=InnoDB;

    create table detail_commande (
        quantite_article integer,
        sous_total_detail_article float(23),
        sous_total_detail_article_apres_promo float(23),
        id_article bigint not null,
        id_commande bigint not null,
        id_detail_commande bigint not null auto_increment,
        primary key (id_detail_commande)
    ) engine=InnoDB;

    create table promotion (
        date_debut_promo date not null,
        date_fin_promo date not null,
        pourcentage_promo float(23),
        id_promotion bigint not null auto_increment,
        primary key (id_promotion)
    ) engine=InnoDB;

    alter table client 
       add constraint UKmhe9ac6rtmg8wrt1f2bfoqpmd unique (id_adresse);

    alter table client 
       add constraint UK9ablpwejn7pvmhi21uc56cnwe unique (id_carte_fidelite);

    alter table article_promotion 
       add constraint FKgh5tfvgdcl8gv19mnwmvdqibo 
       foreign key (id_promotion) 
       references promotion (id_promotion);

    alter table article_promotion 
       add constraint FK4gbllmrhovoyiqtt6dxb13ghm 
       foreign key (id_article) 
       references article (id_article);

    alter table client 
       add constraint FKpxwh4xi98rmanmlb1h88cohl9 
       foreign key (id_adresse) 
       references adresse (id_adresse);

    alter table client 
       add constraint FKmbfwlimo770j1rrxv4ddcso6r 
       foreign key (id_carte_fidelite) 
       references carte_fidelite (id_carte_fidelite);

    alter table commande 
       add constraint FKfh667737scbwrhtkephs23hq 
       foreign key (id_client) 
       references client (id_client);

    alter table detail_commande 
       add constraint FK3t348on52ab82oe4qa2nm7m4v 
       foreign key (id_article) 
       references article (id_article);

    alter table detail_commande 
       add constraint FKak2cq6il1c6q8mtgj1jh03jhh 
       foreign key (id_commande) 
       references commande (id_commande);

    create table adresse (
        id_adresse bigint not null auto_increment,
        ville varchar(50) not null,
        rue varchar(100) not null,
        code_postal varchar(255) not null,
        primary key (id_adresse)
    ) engine=InnoDB;

    create table article (
        prix_article float(23),
        id_article bigint not null auto_increment,
        nom_article varchar(100) not null,
        type_article enum ('BOISSON','DESSERT','SNACK') not null,
        primary key (id_article)
    ) engine=InnoDB;

    create table article_promotion (
        id_article bigint not null,
        id_promotion bigint not null
    ) engine=InnoDB;

    create table carte_fidelite (
        date_creation date not null,
        points_accumules integer,
        id_carte_fidelite bigint not null auto_increment,
        primary key (id_carte_fidelite)
    ) engine=InnoDB;

    create table client (
        date_naissance date not null,
        id_adresse bigint,
        id_carte_fidelite bigint,
        id_client bigint not null auto_increment,
        nom varchar(50) not null,
        prenom varchar(50) not null,
        primary key (id_client)
    ) engine=InnoDB;

    create table commande (
        date_commande date not null,
        total_commande float(23),
        id_client bigint,
        id_commande bigint not null auto_increment,
        status_commande enum ('ANNULEE','EN_COURS','LIVREE') not null,
        primary key (id_commande)
    ) engine=InnoDB;

    create table detail_commande (
        quantite_article integer,
        sous_total_detail_article float(23),
        sous_total_detail_article_apres_promo float(23),
        id_article bigint not null,
        id_commande bigint not null,
        id_detail_commande bigint not null auto_increment,
        primary key (id_detail_commande)
    ) engine=InnoDB;

    create table promotion (
        date_debut_promo date not null,
        date_fin_promo date not null,
        pourcentage_promo float(23),
        id_promotion bigint not null auto_increment,
        primary key (id_promotion)
    ) engine=InnoDB;

    alter table client 
       add constraint UKmhe9ac6rtmg8wrt1f2bfoqpmd unique (id_adresse);

    alter table client 
       add constraint UK9ablpwejn7pvmhi21uc56cnwe unique (id_carte_fidelite);

    alter table article_promotion 
       add constraint FKgh5tfvgdcl8gv19mnwmvdqibo 
       foreign key (id_promotion) 
       references promotion (id_promotion);

    alter table article_promotion 
       add constraint FK4gbllmrhovoyiqtt6dxb13ghm 
       foreign key (id_article) 
       references article (id_article);

    alter table client 
       add constraint FKpxwh4xi98rmanmlb1h88cohl9 
       foreign key (id_adresse) 
       references adresse (id_adresse);

    alter table client 
       add constraint FKmbfwlimo770j1rrxv4ddcso6r 
       foreign key (id_carte_fidelite) 
       references carte_fidelite (id_carte_fidelite);

    alter table commande 
       add constraint FKfh667737scbwrhtkephs23hq 
       foreign key (id_client) 
       references client (id_client);

    alter table detail_commande 
       add constraint FK3t348on52ab82oe4qa2nm7m4v 
       foreign key (id_article) 
       references article (id_article);

    alter table detail_commande 
       add constraint FKak2cq6il1c6q8mtgj1jh03jhh 
       foreign key (id_commande) 
       references commande (id_commande);

    create table adresse (
        id_adresse bigint not null auto_increment,
        ville varchar(50) not null,
        rue varchar(100) not null,
        code_postal varchar(255) not null,
        primary key (id_adresse)
    ) engine=InnoDB;

    create table article (
        prix_article float(23),
        id_article bigint not null auto_increment,
        nom_article varchar(100) not null,
        type_article enum ('BOISSON','DESSERT','SNACK') not null,
        primary key (id_article)
    ) engine=InnoDB;

    create table article_promotion (
        id_article bigint not null,
        id_promotion bigint not null
    ) engine=InnoDB;

    create table carte_fidelite (
        date_creation date not null,
        points_accumules integer,
        id_carte_fidelite bigint not null auto_increment,
        primary key (id_carte_fidelite)
    ) engine=InnoDB;

    create table client (
        date_naissance date not null,
        id_adresse bigint,
        id_carte_fidelite bigint,
        id_client bigint not null auto_increment,
        nom varchar(50) not null,
        prenom varchar(50) not null,
        primary key (id_client)
    ) engine=InnoDB;

    create table commande (
        date_commande date not null,
        total_commande float(23),
        id_client bigint,
        id_commande bigint not null auto_increment,
        status_commande enum ('ANNULEE','EN_COURS','LIVREE') not null,
        primary key (id_commande)
    ) engine=InnoDB;

    create table detail_commande (
        quantite_article integer,
        sous_total_detail_article float(23),
        sous_total_detail_article_apres_promo float(23),
        id_article bigint not null,
        id_commande bigint not null,
        id_detail_commande bigint not null auto_increment,
        primary key (id_detail_commande)
    ) engine=InnoDB;

    create table promotion (
        date_debut_promo date not null,
        date_fin_promo date not null,
        pourcentage_promo float(23),
        id_promotion bigint not null auto_increment,
        primary key (id_promotion)
    ) engine=InnoDB;

    alter table client 
       add constraint UKmhe9ac6rtmg8wrt1f2bfoqpmd unique (id_adresse);

    alter table client 
       add constraint UK9ablpwejn7pvmhi21uc56cnwe unique (id_carte_fidelite);

    alter table article_promotion 
       add constraint FKgh5tfvgdcl8gv19mnwmvdqibo 
       foreign key (id_promotion) 
       references promotion (id_promotion);

    alter table article_promotion 
       add constraint FK4gbllmrhovoyiqtt6dxb13ghm 
       foreign key (id_article) 
       references article (id_article);

    alter table client 
       add constraint FKpxwh4xi98rmanmlb1h88cohl9 
       foreign key (id_adresse) 
       references adresse (id_adresse);

    alter table client 
       add constraint FKmbfwlimo770j1rrxv4ddcso6r 
       foreign key (id_carte_fidelite) 
       references carte_fidelite (id_carte_fidelite);

    alter table commande 
       add constraint FKfh667737scbwrhtkephs23hq 
       foreign key (id_client) 
       references client (id_client);

    alter table detail_commande 
       add constraint FK3t348on52ab82oe4qa2nm7m4v 
       foreign key (id_article) 
       references article (id_article);

    alter table detail_commande 
       add constraint FKak2cq6il1c6q8mtgj1jh03jhh 
       foreign key (id_commande) 
       references commande (id_commande);

    create table adresse (
        id_adresse bigint not null auto_increment,
        ville varchar(50) not null,
        rue varchar(100) not null,
        code_postal varchar(255) not null,
        primary key (id_adresse)
    ) engine=InnoDB;

    create table article (
        prix_article float(23),
        id_article bigint not null auto_increment,
        nom_article varchar(100) not null,
        type_article enum ('BOISSON','DESSERT','SNACK') not null,
        primary key (id_article)
    ) engine=InnoDB;

    create table article_promotion (
        id_article bigint not null,
        id_promotion bigint not null
    ) engine=InnoDB;

    create table carte_fidelite (
        date_creation date not null,
        points_accumules integer,
        id_carte_fidelite bigint not null auto_increment,
        primary key (id_carte_fidelite)
    ) engine=InnoDB;

    create table client (
        date_naissance date not null,
        id_adresse bigint,
        id_carte_fidelite bigint,
        id_client bigint not null auto_increment,
        nom varchar(50) not null,
        prenom varchar(50) not null,
        primary key (id_client)
    ) engine=InnoDB;

    create table commande (
        date_commande date not null,
        total_commande float(23),
        id_client bigint,
        id_commande bigint not null auto_increment,
        status_commande enum ('ANNULEE','EN_COURS','LIVREE') not null,
        primary key (id_commande)
    ) engine=InnoDB;

    create table detail_commande (
        quantite_article integer,
        sous_total_detail_article float(23),
        sous_total_detail_article_apres_promo float(23),
        id_article bigint not null,
        id_commande bigint not null,
        id_detail_commande bigint not null auto_increment,
        primary key (id_detail_commande)
    ) engine=InnoDB;

    create table promotion (
        date_debut_promo date not null,
        date_fin_promo date not null,
        pourcentage_promo float(23),
        id_promotion bigint not null auto_increment,
        primary key (id_promotion)
    ) engine=InnoDB;

    alter table client 
       add constraint UKmhe9ac6rtmg8wrt1f2bfoqpmd unique (id_adresse);

    alter table client 
       add constraint UK9ablpwejn7pvmhi21uc56cnwe unique (id_carte_fidelite);

    alter table article_promotion 
       add constraint FKgh5tfvgdcl8gv19mnwmvdqibo 
       foreign key (id_promotion) 
       references promotion (id_promotion);

    alter table article_promotion 
       add constraint FK4gbllmrhovoyiqtt6dxb13ghm 
       foreign key (id_article) 
       references article (id_article);

    alter table client 
       add constraint FKpxwh4xi98rmanmlb1h88cohl9 
       foreign key (id_adresse) 
       references adresse (id_adresse);

    alter table client 
       add constraint FKmbfwlimo770j1rrxv4ddcso6r 
       foreign key (id_carte_fidelite) 
       references carte_fidelite (id_carte_fidelite);

    alter table commande 
       add constraint FKfh667737scbwrhtkephs23hq 
       foreign key (id_client) 
       references client (id_client);

    alter table detail_commande 
       add constraint FK3t348on52ab82oe4qa2nm7m4v 
       foreign key (id_article) 
       references article (id_article);

    alter table detail_commande 
       add constraint FKak2cq6il1c6q8mtgj1jh03jhh 
       foreign key (id_commande) 
       references commande (id_commande);

    create table adresse (
        id_adresse bigint not null auto_increment,
        ville varchar(50) not null,
        rue varchar(100) not null,
        code_postal varchar(255) not null,
        primary key (id_adresse)
    ) engine=InnoDB;

    create table article (
        prix_article float(23),
        id_article bigint not null auto_increment,
        nom_article varchar(100) not null,
        type_article enum ('BOISSON','DESSERT','SNACK') not null,
        primary key (id_article)
    ) engine=InnoDB;

    create table article_promotion (
        id_article bigint not null,
        id_promotion bigint not null
    ) engine=InnoDB;

    create table carte_fidelite (
        date_creation date not null,
        points_accumules integer,
        id_carte_fidelite bigint not null auto_increment,
        primary key (id_carte_fidelite)
    ) engine=InnoDB;

    create table client (
        date_naissance date not null,
        id_adresse bigint,
        id_carte_fidelite bigint,
        id_client bigint not null auto_increment,
        nom varchar(50) not null,
        prenom varchar(50) not null,
        primary key (id_client)
    ) engine=InnoDB;

    create table commande (
        date_commande date not null,
        total_commande float(23),
        id_client bigint,
        id_commande bigint not null auto_increment,
        status_commande enum ('ANNULEE','EN_COURS','LIVREE') not null,
        primary key (id_commande)
    ) engine=InnoDB;

    create table detail_commande (
        quantite_article integer,
        sous_total_detail_article float(23),
        sous_total_detail_article_apres_promo float(23),
        id_article bigint not null,
        id_commande bigint not null,
        id_detail_commande bigint not null auto_increment,
        primary key (id_detail_commande)
    ) engine=InnoDB;

    create table promotion (
        date_debut_promo date not null,
        date_fin_promo date not null,
        pourcentage_promo float(23),
        id_promotion bigint not null auto_increment,
        primary key (id_promotion)
    ) engine=InnoDB;

    alter table client 
       add constraint UKmhe9ac6rtmg8wrt1f2bfoqpmd unique (id_adresse);

    alter table client 
       add constraint UK9ablpwejn7pvmhi21uc56cnwe unique (id_carte_fidelite);

    alter table article_promotion 
       add constraint FKgh5tfvgdcl8gv19mnwmvdqibo 
       foreign key (id_promotion) 
       references promotion (id_promotion);

    alter table article_promotion 
       add constraint FK4gbllmrhovoyiqtt6dxb13ghm 
       foreign key (id_article) 
       references article (id_article);

    alter table client 
       add constraint FKpxwh4xi98rmanmlb1h88cohl9 
       foreign key (id_adresse) 
       references adresse (id_adresse);

    alter table client 
       add constraint FKmbfwlimo770j1rrxv4ddcso6r 
       foreign key (id_carte_fidelite) 
       references carte_fidelite (id_carte_fidelite);

    alter table commande 
       add constraint FKfh667737scbwrhtkephs23hq 
       foreign key (id_client) 
       references client (id_client);

    alter table detail_commande 
       add constraint FK3t348on52ab82oe4qa2nm7m4v 
       foreign key (id_article) 
       references article (id_article);

    alter table detail_commande 
       add constraint FKak2cq6il1c6q8mtgj1jh03jhh 
       foreign key (id_commande) 
       references commande (id_commande);

    create table adresse (
        id_adresse bigint not null auto_increment,
        ville varchar(50) not null,
        rue varchar(100) not null,
        code_postal varchar(255) not null,
        primary key (id_adresse)
    ) engine=InnoDB;

    create table article (
        prix_article float(23),
        id_article bigint not null auto_increment,
        nom_article varchar(100) not null,
        type_article enum ('BOISSON','DESSERT','SNACK') not null,
        primary key (id_article)
    ) engine=InnoDB;

    create table article_promotion (
        id_article bigint not null,
        id_promotion bigint not null
    ) engine=InnoDB;

    create table carte_fidelite (
        date_creation date not null,
        points_accumules integer,
        id_carte_fidelite bigint not null auto_increment,
        primary key (id_carte_fidelite)
    ) engine=InnoDB;

    create table client (
        date_naissance date not null,
        id_adresse bigint,
        id_carte_fidelite bigint,
        id_client bigint not null auto_increment,
        nom varchar(50) not null,
        prenom varchar(50) not null,
        primary key (id_client)
    ) engine=InnoDB;

    create table commande (
        date_commande date not null,
        total_commande float(23),
        id_client bigint,
        id_commande bigint not null auto_increment,
        status_commande enum ('ANNULEE','EN_COURS','LIVREE') not null,
        primary key (id_commande)
    ) engine=InnoDB;

    create table detail_commande (
        quantite_article integer,
        sous_total_detail_article float(23),
        sous_total_detail_article_apres_promo float(23),
        id_article bigint not null,
        id_commande bigint not null,
        id_detail_commande bigint not null auto_increment,
        primary key (id_detail_commande)
    ) engine=InnoDB;

    create table promotion (
        date_debut_promo date not null,
        date_fin_promo date not null,
        pourcentage_promo float(23),
        id_promotion bigint not null auto_increment,
        primary key (id_promotion)
    ) engine=InnoDB;

    alter table client 
       add constraint UKmhe9ac6rtmg8wrt1f2bfoqpmd unique (id_adresse);

    alter table client 
       add constraint UK9ablpwejn7pvmhi21uc56cnwe unique (id_carte_fidelite);

    alter table article_promotion 
       add constraint FKgh5tfvgdcl8gv19mnwmvdqibo 
       foreign key (id_promotion) 
       references promotion (id_promotion);

    alter table article_promotion 
       add constraint FK4gbllmrhovoyiqtt6dxb13ghm 
       foreign key (id_article) 
       references article (id_article);

    alter table client 
       add constraint FKpxwh4xi98rmanmlb1h88cohl9 
       foreign key (id_adresse) 
       references adresse (id_adresse);

    alter table client 
       add constraint FKmbfwlimo770j1rrxv4ddcso6r 
       foreign key (id_carte_fidelite) 
       references carte_fidelite (id_carte_fidelite);

    alter table commande 
       add constraint FKfh667737scbwrhtkephs23hq 
       foreign key (id_client) 
       references client (id_client);

    alter table detail_commande 
       add constraint FK3t348on52ab82oe4qa2nm7m4v 
       foreign key (id_article) 
       references article (id_article);

    alter table detail_commande 
       add constraint FKak2cq6il1c6q8mtgj1jh03jhh 
       foreign key (id_commande) 
       references commande (id_commande);
