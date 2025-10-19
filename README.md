# BlockysNetwork - KitPvP

Plugin KitPvP simple pour serveur Minecraft (Spigot 1.12.2). Il propose un menu de sélection de kits, un système de spawn configurable et des protections de base pour l'expérience KitPvP.

## Fonctionnalités

- Menu de sélection de kits via la commande `/kit` (Guerrier, Archer, Mage)
- Attribution d'un seul kit par vie (reset à la mort)
- Gestion du spawn global avec `/setspawn`
- Téléportation automatique au spawn à la connexion et à la mort
- Nettoyage de l'inventaire à la connexion et à la mort
- Prévention du PvP tant que le joueur n'a pas choisi de kit
- Annulation des actions indésirables: drop d'items, manipulation d'armor stands
- Messages de join/quit discrets (join broadcast formaté, quit silencieux)

## Prérequis

- Serveur Spigot/Paper 1.12.2
- Java 8 (runtime et pour compiler)
- Maven 3.8+ (pour compiler depuis les sources)

## Installation

1. Téléchargez/compilez le plugin (voir section Compilation)
2. Placez le JAR dans le dossier `plugins/` de votre serveur Spigot/Paper
3. Démarrez le serveur une première fois pour générer la configuration
4. Optionnel: Ajustez le `config.yml` généré, ou utilisez `/setspawn`

## Compilation

Ce projet utilise Maven et dépend de `spigot-api` 1.12.2 (scope `provided`).

- Compiler en local et produire le JAR:

```powershell
mvn -q -e -DskipTests package
```

Le JAR sera disponible dans `target/BlockysKP-1.0.0.jar`.

## Commandes et permissions

- `/kit`
  - Description: Ouvre le menu de sélection des kits
  - Permission: `kit.use`
- `/setspawn`
  - Description: Définit la position du spawn global (monde, coordonnées, orientation) à votre position actuelle
  - Permission: `setspawn.use`

Assignez les permissions via votre gestionnaire de permissions (LuckPerms, PermissionsEx, etc.).

## Configuration

Fichier: `plugins/BlockysKP/config.yml`

```yaml
spawn:
  world: world
  x: 0
  y: 64
  z: 0
  yaw: 0
  pitch: 0
```

- Vous pouvez modifier ces valeurs manuellement ou exécuter `/setspawn` en jeu pour les mettre à jour automatiquement.

## Gameplay des kits

Le menu propose 3 kits de base:
- Guerrier: épée en fer
- Archer: arc + 32 flèches
- Mage: bâton (blaze rod)

Règles:
- Un seul kit par vie: une fois un kit choisi, vous ne pouvez plus rouvrir le menu jusqu'à votre prochaine mort
- PvP autorisé uniquement entre joueurs ayant choisi un kit
- À la mort: téléportation au spawn, inventaire vidé, et possibilité de reprendre un kit

## Compatibilité et limites

- Version ciblée: Spigot/Paper 1.12.2 (API 1.12.2-R0.1-SNAPSHOT)
- Le plugin utilise des API basiques (inventaires, événements joueurs, téléportation) et doit rester compatible avec la plupart des plugins de permissions et de gestion de monde
- Si le monde configuré pour le spawn n'existe pas, une alerte est envoyée au joueur et la téléportation est annulée

## Dépannage

- La commande `/kit` ne fait rien: vérifiez la permission `kit.use` et les erreurs éventuelles dans la console
- Téléportation au spawn impossible: assurez-vous que `spawn.world` correspond au nom exact d'un monde chargé
- Impossible d'utiliser `/setspawn`: vérifiez la permission `setspawn.use`

## Développement

- Point d'entrée: `com.BlockysKP.Main`
- Commandes: `com.BlockysKP.Commands.Kit`, `com.BlockysKP.Commands.SetSpawn`
- Menu: `com.BlockysKP.Menu.KitMenu`
- Utilitaires: `com.BlockysKP.Utils.SpawnUtils`, `com.BlockysKP.Utils.EventUtils`

Structure Maven minimale avec `maven-compiler-plugin` (release 8) et `spigot-api` en `provided`.

## Auteurs

- Auteur du plugin: D-WADE (plugin.yml)
- Repo: BlockysNetwork-KitPvP — LoanALLARD

## Licence

Ce projet n'inclut pas encore de licence explicite. Ajoutez un fichier `LICENSE` si vous souhaitez clarifier les conditions d'utilisation et de redistribution.
