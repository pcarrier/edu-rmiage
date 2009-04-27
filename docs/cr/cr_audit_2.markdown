Compte-rendu du deuxième audit (24 avril 2009, 9h15)
====================================================

CR du premier RDV convient à tous.

Point sur l'avancement
----------------------

**Effectué :**

- Première répartition des tâches (il est prévu de s'adapter au fur et à mesure)
- Première ébauche de schéma UML
- Avancement important du cahier des charges dans le respect partiel de la norme AFNOR

**Changements :**

Contrairement à ce qui était vu jusque là, l'organisation ne relève pas du CdC mais du plan de développement logiciel (notamment pas de diagramme de Gantt).

Choix discutés pour la suite
----------------------------

**Technique :**

- Stockage relationnel (donc Hibernate) pertinent
- Cependant coûteux en temps
- -> si les contraintes de temps amènent à utiliser la sérialisation, abstraire le stockage via une architecture modulaire (cette discussion introduit la distinction entre besoins primaires et besoins secondaires)
- Erreurs à corriger dans le schéma de l'architecture logicielle
- Sécurité : les objets exposés par RMI seront des objets de manipulation de contenu et non du contenu afin d'y assurer l'application des permissions.
- Notifications du client : le serveur pourra distribuer par RMI un objet de notification dont une méthode retourne les événements afin d'en notifier immédiatement le client. Cela permet de regrouper messageries "E-mail" et instantanée.
- La généricité du client peut être assurée avec la livraison dynamique par le serveur de JPanel adaptés aux objets à manipuler. Cependant ce serait inutilisable si toute la gestion graphique revient au serveur. Il faut chercher à fournir des classes par RMI.

**Organisationnel :**

- Toute l'organisation de l'équipe et la planification relèvent du PDL
- F. Puitg apportera des informations complémentaire sur sa nature après contact de P. Morat.
- Quelques transparents seraient un plus pour la réunion de lundi. Reprendre simplement les diagrammes du CdC et PDL convient.