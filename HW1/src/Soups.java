interface ISoup {
}

class Broth implements ISoup {
  String type;
  
  Broth(String type) {
    this.type = type;
  }
}

class Ingredient implements ISoup {
  ISoup more;
  String name;
  
  Ingredient(ISoup more, String name) {
    this.more = more;
    this.name = name;
  }
}

class ExamplesSoup {
  ISoup chicken = new Broth("chicken");
  ISoup vanilla = new Broth("vanilla");
 
  ISoup yummy = new Ingredient(new Ingredient(new Ingredient(this.chicken,"carrots"),"celery"),
      "noodles");
  ISoup noThankYou = new Ingredient(new Ingredient(new Ingredient(this.vanilla,"horseradish"),
      "hot dogs"),"plum sauce");
  
  
}