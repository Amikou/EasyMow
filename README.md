# EasyMow

Le datamodel : Cardinal : Un point cardinal. Contient un selead trait Cardinal et des case object des différents points cardinaux.
               Command : Une commande. Contient un selead trait Command et les différentes commandes : G, D et A.
               Field : Le terrain. Il est caractérisé par une hauteur et une longueur.
               Position : Une position sur un terrain. Caractérisé par une position en x et y ainsi qu'un field.
                          Associer le terrain à une position peut ne pas être instinctif mais cela permet de résoudre les problèmes
                          de dépassement des limites du terrain lors de l'éxécution des commandes d'une Tondeuse.
               Tondeuse : Une tondeuse. Caractérisée par une option de position, une option de cardinal et une liste d'option 
                          de command. 
                  
   
Est utilisé le pattern commande et factory. 

Pattern factory : Ainsi chaque object du datamodel a une factory permettant de construire une instance 
                  d'option de l'objet pour ainsi gérer les différents cas d'erreurs. Par exemple, dans le cas d'une tondeuse, 
                  si on essaye de construire une tondeuse à partir d'une option position définie à None, on va renvoyer None.
                  
Pattern Commande : Chaque command a le trait Command, et donc un char représentant la commande à taper pour l'invoquer
                   ( G, D ou A ) ainsi qu'une def execute sur une Tondeuse afin d'éxecuter la commande sur celle-ci.
                   
                   
                   
Notes : En cas de dépassement des limites du terrain par la tondeuse, on la replace automatiquement au bord le plus proche des 
        limites du terrain. Ex : Vouloir construire une tondeuse avec une Position (-666, 2) sur un Field(2,2) construit
        en fait une tondeuse avec une Position (0,2). Les collisions inter tondeuses ne sont pas gérées. Créer une tondeuse avec
        une orientation ( Cardinal ) inconnue entrainera l'auto destruction par implosion de la tondeuse.
        
                   

