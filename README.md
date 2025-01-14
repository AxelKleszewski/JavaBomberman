# *Bomberman* en JavaFX

## Description

Ce projet fournit une implantation de base du jeu *Bomberman* en *JavaFX*.
Pour pouvoir développer votre propre implantation de ce projet, vous devez
en créer une **divergence** en cliquant sur le bouton `Fork` en haut à droite
de cette page.

Lorsque ce sera fait, vous pourrez inviter les membres de votre groupe en tant
que *Developer* pour vous permettre de travailler ensemble sur ce projet.

## Consignes

Vous pouvez retrouver ci-dessous les liens vers les sujets de TP vous guidant
dans le développement de votre projet.

- [Lancement du projet](https://gitlab.univ-artois.fr/enseignements-rwa/modules/but-2/r3-04/tp/-/tree/main/TP03)
- Pour avoir le exe, lancer dans le terminal la commande gradle jlink puis lancer le fichier build/image/bin/bomberman.bat (on n'a pas réussis à fournir un exe qui marche car le build est trop volumineux et l'exe ne marche pas sans le build)

## Diagramme de classes

```plantuml
hide empty members

scale 1000 width
scale 700 height

class Bomberman {
    - {static} GAME_WIDTH: int
    - {static} GAME_HEIGHT: int
    - {static} NB_ENEMIES: int

    + start(stage: Stage): void
    + {static} main(args: String[]): void
}
Bomberman --> BombermanController : << charge >>
Bomberman --> BombermanGame : << crée >>

class BombermanGame {
    + {static} RANDOM: Random
    + {static} DEFAULT_SPEED: int
    + {static} DEFAULT_BOMBS: int
    - width: int
    - height: int
    - spriteStore: ISpriteStore
    - gameMap: GameMap
    - player: Joueur
    - nbEnemies: int
    - remainingEnemies: int
    - movableObjects: List<IMovable>
    - animation: BombermanAnimation
    - controller: IBombermanController

    + BombermanGame(gameWidth: int, gameHeight: int, spriteStore: ISpriteStore, nbEnemies: int)
    + setController(controller: IBombermanController): void
    + getWidth(): int
    + getHeight(): int
    + prepare(): void
    - createMap(): GameMap
    + start(): void
    - createMovables(): void
    - initStatistics(): void
    - spawnMovable(movable: IMovable): void
    + moveUp(): void
    + moveRight(): void
    + moveDown(): void
    + moveLeft(): void
    + stopMoving(): void
    + dropBomb(): void
    + dropBomb(bomb: IMovable): void
    - getCellOf(movable: IMovable): Cell
    + getCellAt(x: int, y: int): Cell
    + addMovable(object: IMovable): void
    + removeMovable(object: IMovable): void
    - clearAllMovables(): void
    + enemyIsDead(enemy: IMovable): void
    + playerIsDead(): void
    - gameOver(message: String): void
}
BombermanGame o-- "1" ISpriteStore
BombermanGame *-- "1" GameMap
BombermanGame *-- "*" IMovable
BombermanGame *-- "1" BombermanAnimation
BombermanGame o-- "1" IBombermanController

class BombermanAnimation {
    - movableObjects: List<IMovable>
    - previousTimestamp: long

    + BombermanAnimation(movableObjects: List<IMovable>)
    + start(): void
    + handle(now: long): void
    - moveObjects(delta: long): void
    - checkCollisions(): void
}
BombermanAnimation o-- "*" IMovable

interface IBombermanController{
    + {abstract} setGame(game: BombermanGame): void
    + {abstract} prepare(map: GameMap): void
    + {abstract} bindScore(scoreProperty: IntegerExpression): void
    + {abstract} bindBombs(bombsProperty: IntegerExpression): void
    + {abstract} bindLife(lifeProperty: IntegerExpression): void
    + {abstract} addMovable(movable: IMovable): void
    + {abstract} gameOver(endMessage: String): void
    + {abstract} reset(): void
}

class BombermanController implements IBombermanController {
    - game: BombermanGame
    - stage: Stage
    - backgroundPane: GridPane
    - movingPane: Pane
    - score: Label
    - bombs: Label
    - life: Label
    - message: Label
    - started: boolean

    + setStage(stage: Stage): void
    + setGame(game: BombermanGame): void
    + prepare(map: GameMap): void
    - createBackground(map: GameMap): void
    - addKeyListeners(): void
    + bindScore(scoreProperty: IntegerExpression): void
    + bindBombs(bombsProperty: IntegerExpression): void
    + bindLife(lifeProperty: IntegerExpression): void
    + addMovable(movable: IMovable): void
    + gameOver(endMessage: String): void
    + reset(): void
}
BombermanController o-- "1" BombermanGame

interface IMovable {
    + {abstract} getWidth(): int
    + {abstract} getHeight(): int
    + {abstract} setX(xPosition: int): void
    + {abstract} getX(): int
    + {abstract} getXProperty(): DoubleProperty
    + {abstract} setY(yPosition: int): void
    + {abstract} getY(): int
    + {abstract} getYProperty(): DoubleProperty
    + {abstract} consume(): void
    + {abstract} isConsumed(): boolean
    + {abstract} isConsumedProperty(): BooleanProperty
    + {abstract} setHorizontalSpeed(speed: double): void
    + {abstract} getHorizontalSpeed(): double
    + {abstract} setVerticalSpeed(speed: double): void
    + {abstract} getVerticalSpeed(): double
    + {abstract} setSprite(sprite: Sprite): void
    + {abstract} getSprite(): Sprite
    + {abstract} getSpriteProperty(): ObjectProperty<Sprite>
    + {abstract} move(timeDelta: long): boolean
    + {abstract} isCollidingWith(other: IMovable): boolean
    + {abstract} collidedWith(other: IMovable): void
    + {abstract} explode(): void
    + {abstract} hitEnemy(): void
    + {abstract} self(): IMovable
}

abstract class AbstractMovable implements IMovable {
    - {static} MARGIN: int
    # game: BombermanGame
    # xPosition: DoubleProperty
    # yPosition: DoubleProperty
    # consumed: BooleanProperty
    # horizontalSpeed: double
    # verticalSpeed: double
    # sprite: ObjectProperty<Sprite>

    # AbstractMovable(game: BombermanGame, xPosition: double, yPosition: double, sprite: Sprite)
    + getWidth(): int
    + getHeight(): int
    + setX(xPosition: int): void
    + getX(): int
    + getXProperty(): DoubleProperty
    + setY(yPosition: int): void
    + getY(): int
    + getYProperty(): DoubleProperty
    + consume(): void
    + isConsumed(): boolean
    + isConsumedProperty(): BooleanProperty
    + setHorizontalSpeed(speed: double): void
    + getHorizontalSpeed(): double
    + setVerticalSpeed(speed: double): void
    + getVerticalSpeed(): double
    + setSprite(sprite: Sprite): void
    + getSprite(): Sprite
    + getSpriteProperty(): ObjectProperty<Sprite>
    + move(timeDelta: long): boolean
    - isOnWall(x: int, y: int): boolean
    + isCollidingWith(other: IMovable): boolean
    + collidedWith(other: IMovable): void
    + explode(): void
    + hitEnemy(): void
    + self(): IMovable
    + hashCode(): int
    + equals(obj: Object): boolean
}
AbstractMovable *-- "1" BombermanGame
AbstractMovable o-- "1" Sprite

class GameMap {
    - height: int
    - width: int
    - cells: Cell[][]

    + GameMap(height: int, width: int)
    - init(): void
    + getHeight(): int
    + getWidth(): int
    + isOnMap(row: int, column: int): boolean
    + getAt(row: int, column: int): Cell
    + setAt(row: int, column: int, cell: Cell): void
    + getEmptyCells(): List<Cell>
}
GameMap *-- "*" Cell

class Cell {
    - row: int
    - column: int
    - spriteProperty: ObjectProperty<Sprite>
    - wallProperty: ObjectProperty<Wall>

    + Cell(row: int, column: int)
    + Cell(sprite: Sprite)
    # Cell(wall: Wall)
    + getRow(): int
    + getColumn(): int
    + getWidth(): int
    + getHeight(): int
    + isEmpty(): boolean
    + getSprite(): Sprite
    + getSpriteProperty(): ObjectProperty<Sprite>
    + getWall(): Wall
    + getWallProperty(): ObjectProperty<Wall>
    + replaceBy(cell: Cell): void
}
Cell o-- "1" Sprite
Cell *-- "0..1" Wall

class Wall {
    - sprite: Sprite

    + Wall(sprite: Sprite)
    + getSprite(): Sprite
}

interface ISpriteStore {
    + {abstract} getSprite(identifier: String): Sprite
    + getSpriteSize(): int
}
ISpriteStore --> Sprite : << crée >>

class SpriteStore implements ISpriteStore {
    - spriteCache: Map<String, Sprite>
    + getSprite(identifier: String): Sprite
    - loadImage(name: String): Image
}

class Sprite {
    - image: Image

    + Sprite(image: Image)
    + getWidth(): int
    + getHeight(): int
    + getImage(): Image
    + draw(graphics: GraphicsContext, x: int, y: int): void
}

class LivingMovableDecorator{
    # movable: IMovable
    # lives: int
    # LivingMovableDecorator(movable: IMovable , lives: int)
    + decreaseLives(): void
}

LivingMovableDecorator ..|> IMovable
LivingMovableDecorator <-- IMovable

class Enemy{
    + move(timeDelta: long)): void
    + collidedWith(other: IMovable): void
    + explode(): void
    + hitEnemy(): void
}
Enemy --|> AbstractMovable

interface IStrategyMove{
    + move(boolean isOnWall): void;
}

class DefaultMove {
    - enemy: Enemy
    + move(enemy: Enemy)
}
IStrategyMove <|.. DefaultMove
DefaultMove --> Enemy

class IntelligentMove {
    - enemy: Enemy
    - Player: player
    + move(enemy: Enemy, player: Player)
}
IStrategyMove <|.. IntelligentMove
IntelligentMove <-- Enemy
IntelligentMove <-- Player

class RandomMove {
    - enemy: Enemy
    + move(enemy: Enemy)
}

IStrategyMove <|.. RandomMove
RandomMove <-- Enemy

interface IPlayer {
    + IPlayer nextState()
    + void damaged(Player player)
    + void removeInv(Player player)
    + void decreaseLives(LivingPlayer player)
}

class InvulnerablePlayer implements IPlayer {
    - long time
    - static final int INVULNERABLE_TIME_MILLIS = 2000
    + InvulnerablePlayer()
    + IPlayer nextState()
    + void damaged(Player player)
    + void removeInv(Player player)
    + void decreaseLives(LivingPlayer player)
}

class VulnerablePlayer implements IPlayer {
    + IPlayer nextState()
    + void damaged(Player player)
    + void removeInv(Player player)
    + void decreaseLives(LivingPlayer player)
}

class LivingEnemy{
    - timeLastHit: long
    - INVULNERABLE_TIME_MILLIS: int
    + LivingEnemy(game: BombermanGame, xPosition: double, yPosition: double, sprite: Sprite, life: int)
    + getEnemy(): Enemy
}

LivingEnemy --|> LivingMovableDecorator

class Explosion extends AbstractMovable {
    - long creationTime
    - final long LIFETIME = 1000
    + move(long delta): boolean
    + collidedWith(other: IMovable): void
    + explode(): void
    + hitEnemy(): void
}

interface IBomb {
    +void explosion(BombermanGame game, double xPosition, double yPosition)
}

class Bomb {
    -IBomb explosionStrategy
    -long duration
    -long time
    +Bomb(BombermanGame game, double xPosition, double yPosition, Sprite sprite, IBomb explosionStrategy)
    +void collidedWith(IMovable other)
    +void explode()
    +void hitEnemy()
    +void positionBombe()
    +void explosion(BombermanGame game, double xPosition, double yPosition)
    +boolean move(long delta)
    +boolean equals(Object o)
    +int hashCode()
}

IMovable <|-- AbstractMovable
AbstractMovable <|-- Bomb
IBomb <|..> Bomb

class ColumnBomb implements IBomb {
    +void explosion(BombermanGame game, double xPosition, double yPosition)
}

class LargeBomb implements IBomb {
    +void explosion(BombermanGame game, double xPosition, double yPosition)
}

class RowBomb implements IBomb {
    +void explosion(BombermanGame game, double xPosition, double yPosition)
}

class SimpleBomb implements IBomb {
    +void explosion(BombermanGame game, double xPosition, double yPosition)
}

class Player {
    - IntegerProperty score
    - ObservableList<Bomb> listBombs
    - IPlayer playerState
    + Player(game: BombermanGame, xPosition: double, yPosition: double, sprite: Sprite, score: int)
    + int getScore()
    + IntegerProperty scoreProperty()
    + void setScore(score: int)
    + ObservableList<Bomb> getListBombs()
    + void setListBombs(listBombs: ObservableList<Bomb>)
    + IPlayer getPlayerState()
    + void setPlayerState(playerState: IPlayer)
    + BombermanGame getGame()
    + void addBombs(bomb: Bomb)
    + void removeBomb(bomb: Bomb)
    + boolean move(delta: long)
    + void collidedWith(other: IMovable)
    + void explode()
    + void hitEnemy()
    + boolean equals(o: Object)
    + int hashCode()
}

IPlayer <-- Player

Player --|> AbstractMovable
LivingPlayer -- LivingMovableDecorator
LivingPlayer <-- Player

class LivingPlayer {
    - player: Player
    - livesProperty: IntegerProperty 
    + LivingPlayer(game: BombermanGame, xPosition: double, yPosition: double, sprite: Sprite, score: int, lives: int)
    + getLivesProperty(): IntegerProperty
    + getJoueur(): Player
    + getScore(): int
    + getScoreProperty(): IntegerProperty
    + setScore(score: int)
    + addBombs(bomb: Bomb)
    + removeBomb(bomb: Bomb)
    + getListBombs(): ObservableList<Bomb>
}

interface IStateWall {
  + nextState(): IStateWall
  + currentState(): IStateWall
}

class Wall {
  + Wall(Sprite) 
  + Wall() 
  + explose(): void
  - destroyed: boolean
  - sprite: Sprite
}

class WallState1 {
  + WallState1() 
  + nextState(): IStateWall
  + currentState(): IStateWall
  - sprite: Sprite
}
class WallState2 {
  + WallState2() 
  + nextState(): IStateWall
  + currentState(): IStateWall
  - sprite: Sprite
}

interface IMap {
  + initializeMap(GameMap): void
}

class MapClassic {
  + MapClassic() 
  + initializeMap(GameMap): void
}
class MapFlat {
  + MapFlat() 
  + initializeMap(GameMap): void
}
class MapX {
  + MapX() 
  + initializeMap(GameMap): void
}
class RandomMap {
  + RandomMap() 
  + chooseMap(): IMap
}

interface LevelAbstractFactory {
    + createMap(gameMap: GameMap): IMap
    + createEnemy(cell: Cell, sprite: Sprite, game: BombermanGame, player: Player): LivingEnemy
    + getNextLevelFactory(): LevelAbstractFactory
    + createPlayer(game: BombermanGame, sprite: Sprite): LivingPlayer
}

class EasyGameFactory {
    - INSTANCE: LevelAbstractFactory
    + createMap(gameMap: GameMap): IMap
    + createEnemy(cell: Cell, sprite: Sprite, game: BombermanGame, player: Player): LivingEnemy
    + getNextLevelFactory(): LevelAbstractFactory
    + getInstance(): LevelAbstractFactory
}

class MediumGameFactory {
    - INSTANCE: MediumGameFactory
    + createMap(gameMap: GameMap): IMap
    + createEnemy(cell: Cell, sprite: Sprite, game: BombermanGame, player: Player): LivingEnemy
    + getNextLevelFactory(): LevelAbstractFactory
    + getInstance(): LevelAbstractFactory
}

class HardGameFactory {
    - INSTANCE: HardGameFactory
    + createMap(gameMap: GameMap): IMap
    + createEnemy(cell: Cell, sprite: Sprite, game: BombermanGame, player: Player): LivingEnemy
    + getNextLevelFactory(): LevelAbstractFactory
    + getInstance(): LevelAbstractFactory
}

class BonusAddBomb extends AbstractMovable {
    - BombermanGame game
    - double xPosition
    - double yPosition
    - Sprite sprite
    + BonusAddBomb(BombermanGame game, double xPosition, double yPosition, Sprite sprite)
    + collidedWith(IMovable other)
    + explode()
    + hitEnemy()
    + hitBonusAddBomb()
    + hitBonusInvincible()
    + hitBonusLife()
}

class BonusInvincibility extends AbstractMovable {
    - BombermanGame game
    - double xPosition
    - double yPosition
    - Sprite sprite
    + BonusInvincibility(BombermanGame game, double xPosition, double yPosition, Sprite sprite)
    + collidedWith(IMovable other)
    + explode()
    + hitEnemy()
    + hitBonusAddBomb()
    + hitBonusInvincible()
    + hitBonusLife()
}

class BonusLife extends AbstractMovable {
    - BombermanGame game
    - double xPosition
    - double yPosition
    - Sprite sprite
    + BonusLife(BombermanGame game, double xPosition, double yPosition, Sprite sprite)
    + collidedWith(IMovable other)
    + explode()
    + hitEnemy()
    + hitBonusAddBomb()
    + hitBonusInvincible()
    + hitBonusLife()
}

EasyGameFactory --|> LevelAbstractFactory
MediumGameFactory --|> LevelAbstractFactory
HardGameFactory --|> LevelAbstractFactory

BombermanGame --> LevelAbstractFactory
Bomberman --> RandomMap : << choose a map >>
RandomMap --> IMap : << génére >>

IMap <|.. MapClassic
IMap <|.. MapFlat
IMap <|.. MapX

Wall --> IStateWall : << utilise >>

IStateWall <|.. WallState1
IStateWall <|.. WallState2

   
@enduml
```

## Tâches réalisées

### TP n°3

| Fonctionnalité                         | Terminée ? | Auteur(s)       |
| -------------------------------------- | ---------- | --------------- |
| Représentation des ennemis             | oui        | Romain          |
| Intégration des ennemis dans la partie | oui        | Romain          |
| Représentation du joueur               | oui        | Axel            |
| Intégration du joueur dans la partie   | oui        | Axel & Victor   |
| Représentation des bombes et explosion | oui        | Maxime & Romain |
| Intégration des bombes dans la partie  | oui        | Everyone        |
| Création de la carte du jeu            | oui        | Victor          |

### TP n°4

| Fonctionnalité                              | Patron de conception utilisé | Terminée ? | Auteur(s) |
| ------------------------------------------- | ---------------------------- | ---------- | --------- |
| Variantes de déplacement des ennemis        | Stratégie                    | oui        | Romain    |
| Gestion des points de vie (ennemis, joueur) | Décorateur                   | oui        | Romain    |
| Invulnérabilité du joueur                   | Etat                         | oui        | Axel      |
| Solidité des murs                           | Etat                         | oui        | Victor    |
| Variantes de génération pour la carte       | Stratégie                    | oui        | Victor    |
| Différents types de bombes                  | Stratégie                    | oui        | Maxime    |

### TP n°5

| Fonctionnalité                              | Patron de conception utilisé | Terminée ? | Auteur(s) |
| ------------------------------------------- |-----------------------------|-----------|-----------|
| Instance du `SpriteStore`                   | Singleton                   | oui       | Victor    |
| Bonus de bombe                              | Strategie                   | oui       | Axel & Maxime     |
| Bonus d'invulnérabilité                     | Strategie                   | oui       | Axel      |
| Bonus de point de vie                       | Strategie                   | oui       | Axel      |
| Apparition aléatoire d'un bonus             | Strategie                   | oui       | Axel & Victor      |
| Gestion des différents niveaux              | Strategie                   | oui       | Romain    |
