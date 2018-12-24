# EasyMow

Le datamodel :

               Cardinal : Un point cardinal. Contient un selead trait Cardinal et des case object des différents points 
                          cardinaux.
               Command : Une commande. Contient un selead trait Command et les différentes commandes : G, D et A.
               
               Field : Les limites du terrain. Il est caractérisé par une hauteur et une longueur.
               
               Position : Une position sur un terrain. Caractérisé par une position en x et y ainsi qu'un field.
                          Associer le terrain à une position permet de rendre la tondeuse indépendante ( l'information des
                          limites du terrain est contenue dans l'objet tondeuse )
                          
               Tondeuse : Une tondeuse. Caractérisée par une option de position, une option de cardinal et une liste d'option 
                          de command. 
                          
               TondeuseHub : L'objet contenant la liste des tondeuses. Les tondeuses étant indépendantes, il n'est pas 
                             obligatoire de passer par une tondeuseHub.
                             
Patterns de conception :

               Pattern factory : Chaque object du datamodel a une factory permettant de construire une instance 
                  (d'option de) l'objet pour ainsi gérer les différents problèmes. Par exemple, dans le cas d'une tondeuse, 
                  si on essaye de construire une tondeuse à partir d'une option position définie à None, on renvoie None.
               
               Pattern Commande : Chaque command a le trait Command, et donc un char représentant la commande à taper pour
                  l'invoquer ( G, D ou A ) ainsi qu'une def execute sur une Tondeuse et une liste de tondeuse optionnelle 
                  afin d'éxecuter la commande sur celle-ci en gérant les collisions sur les tondeuses de la liste si précisée.
                  Cela permet, entre autres, de pouvoir faire un simple fold pour exécuter les instructions d'une tondeuse.
                 
                   
Choix d'implémentation :  

        En cas de dépassement des limites du terrain par la tondeuse, on la replace automatiquement au bord le plus proche des 
        limites du terrain. Ex : Vouloir construire une tondeuse avec une Position (-666, 2) sur un Field(2,2) construit
        en fait une tondeuse avec une Position (0,2).
        
        Créer une tondeuse avec une orientation ( Cardinal ) inconnue entrainera l'auto destruction de la tondeuse.
        
        Au chargement du fichier ( ou de la string ) les collision sont gérées de manière à n'avoir qu'une seule tondeuse
        par position. Tenter de créer une tondeuse sur une position déjà prise par une autre tondeuse entraîne son
        autodestruction
        
        Les collisions sont aussi gérées à l'éxécution des commandes de sorte à ne garder toujours qu'une seule tondeuse par 
        position. Les tondeuses tentant de se déplacer à une position déjà occupée par une autre tondeuse s'autodétruisent.
        Les tondeuses détruites n'occupent plus de position. Ex : une tondeuse x (1,1) se déplaçant en (1,2) alors qu'une autre
        tondeuse y (1,2) existe, fera s'auitodétruire la tondeuse x. Par suite, déplacer une tondeuse z (0,1) en (1,1) ne pose
        aucun problème.
        
        
        
Fait avec SBT et Intellij. Avec intellij il suffit de check from version control le projet avec git, ensuite d'import avec sbt, d'attendre un peu ( vérifier le sbt shell qu'il fasse le build ) et ensuite on peut run ! Le main se trouve dans le package fr.upem.easymow.main et le fichier txt de config dans ressources.
