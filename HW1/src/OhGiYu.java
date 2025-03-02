interface IResource {
}

class Monster implements IResource {
  String name;
  int hp;
  int attack;
  
  Monster(String name, int hp, int attack) {
    this.name = name;
    this.hp = hp;
    this.attack = attack;
  }
}

class Fusion implements IResource {
  String name;
  Monster monster1;
  Monster monster2;
  
  Fusion(String name, Monster monster1, Monster monster2) {
    this.name = name;
    this.monster1 = monster1;
    this.monster2 = monster2;
  }
}

class Trap implements IResource {
  String description;
  boolean continuous;
  
  Trap(String description, boolean continuous) {
    this.description = description;
    this.continuous = continuous;
  }
}

interface IAction{
}

class Attack implements IAction {
  Monster attacker;
  Monster defender;
  
  Attack(Monster attacker, Monster defender) {
    this.attacker = attacker;
    this.defender = defender;
  }
}

class Activate implements IAction {
  Trap trap;
  IResource target;
  
  Activate(Trap trap, IResource target) {
    this.trap = trap;
    this.target = target;
  }
}

class ExamplesGame {
  Monster kuriboh = new Monster("Kuriboh", 200, 100);
  Monster jinzo = new Monster("Jinzo", 500, 400);
  Fusion kurizo = new Fusion("Kurizo", this.kuriboh, this.jinzo);
  Trap trapHole = new Trap("Kills a monster", false);
  
  Monster rocket = new Monster("Rocks", 80, 300);
  Monster chill = new Monster("chill", 60, 300);
  
  IAction attack1 = new Attack(jinzo,kuriboh);
  IAction attack2 = new Attack(chill, rocket);
  
  IAction activate1 = new Activate(trapHole, jinzo);
  IAction activate2 = new Activate(trapHole, rocket);
}









