# Contributing to BossTroll

BossTroll is an open source project ❤️️

I generally accept useful pull requests that fix bugs or add feautes. Please remember that this plugin is developed for Spigot 1.8 and changes that are only supported by newer (or older) versions will not go to the next release.

Please test everything you add to the plugin extensively to make sure there are no fatal bugs. Consider to comment your code using normal comments and JavaDocs so that other developers can understand your changes and reviewing your pull request is easier.

### Conventions

The code & general project should have a consistent design, so there are some important conventions to follow:

- Indent lines with **4 spaces**
- Every class should have a header containing authors and basic tasks for the class
- Your lines should be as short as possible. Try to not to exceed 100 characters per line
- Try to avoid unnecessary abbreviations in field, method or class names
- Give your commits good names and follow [this guide](https://chris.beams.io/posts/git-commit/).



### Adding new trolls 

All trolls are based on Java interfaces called "templates". You can find them in `*de.pxav.bosstroll.trolls.templates*`. Create a new class in the corresponding package (`player` for trolls that should only be applied for a certain player and `server` for trolls that should affect all players on the server).



Implement one of the templates to your class and create the required methods like so:

```java
package de.pxav.bosstroll.trolls.player;

import de.pxav.bosstroll.trolls.templates.UniqueTroll;
import org.bukkit.entity.Player;

/**
 * Describe what your troll does
 *
 * @author your name
 */
public class ExampleTroll implements UniqueTroll {
    
    // you eventually will have to inject the other instances here
    // via constructor to access other classes. 
    
    @Override
    public void execute(Player player) {
        player.sendMessage("you are getting trolled!");
    }
    
}
```

In this case I implemented `UniqueTroll` as a template, which means that when it's executed only one simple event can happen. What the other templates do is documented in the corresponding class.



To register the troll in the inventory you can simply go the `PlayerTrollInventory` class in `#open(player)` method. Simply add this below the other "set item" actions:

```java
inventory.setItem(24, new ItemBuilder(Material.BANNER, 1) // change the material and slot
                .setDisplayName("§cGive your troll a name")
                .setLore(new String[] {
                        "§7Describe your troll briefly",
                        "§7in this lore."
                })
                // this method adds a new click listener. Execute your troll here.  
                .addListener(player, inventory.getName(), event -> {
                    event.setCancelled(true);
                    this.main.getExampleTroll().execute(
           Bukkit.getPlayer(this.main.getPlayerInfo().getPlayersTrolling().get(player.getUniqueId()))
                    );
                    player.closeInventory();
                })
                .build()
        );
```

Don't forget to save an instance of the troll in the main class to access it in this method.



### Dependency Management

The plugin is using the **D**ependeny **I**njection (DI) pattern. It is planed to use Google Guice to simplify the system and get rid of the God Dependency (`main`). Until this is done, please use the current system.



### Conditions

If you contribute to BossTroll you will get mentioned on the SpigotMC resource page. The rights for the entire BossTroll project are still owned by pxav.