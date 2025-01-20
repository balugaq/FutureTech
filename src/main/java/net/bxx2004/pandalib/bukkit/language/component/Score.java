package net.bxx2004.pandalib.bukkit.language.component;

import me.clip.placeholderapi.PlaceholderAPI;
import net.bxx2004.pandalib.bukkit.language.Text;
import net.bxx2004.pandalib.bukkit.language.TextType;
import net.bxx2004.pandalib.bukkit.language.abandon.PMessage;
import net.bxx2004.pandalib.bukkit.task.Task;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.List;

/**
 * 记分板工具
 */
public class Score extends Text {
    private Scoreboard scoreboard;
    private String id;
    private List<String> teamNames = new ArrayList<>();
    private String[] content;

    /*
     public Score(String id,String title,RenderType type){
        this(id,title,type,Bukkit.getScoreboardManager().getNewScoreboard());
    }
    public Score(String id,String title,RenderType type,Scoreboard scoreboard){
        this.id = id;
        Objective objective = scoreboard.registerNewObjective(id,"dummy",PMessage.replace(title), type);
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        this.scoreboard = scoreboard;
    }
     */
    public Score content(int score, String... contents) {
        content = contents;
        teamNames.clear();
        for (int i = content.length - 1; i >= 0; i--) {
            Team team = scoreboard.registerNewTeam("" + i);
            team.addEntry("§" + ChatColor.values()[i].getChar());
            teamNames.add(team.getName());
            team.setDisplayName("");
            team.setPrefix(PMessage.replace(content[i]));
            if (score == -1) {
                scoreboard.getObjective(id).getScore("§" + ChatColor.values()[i].getChar()).setScore(1);
            } else {
                scoreboard.getObjective(id).getScore("§" + ChatColor.values()[i].getChar()).setScore(i);
            }
        }
        return this;
    }

    public Score refresh(Player player) {
        for (int i = content.length - 1; i >= 0; i--) {
            for (String teamName : teamNames) {
                scoreboard.getTeam(teamName).setPrefix(PlaceholderAPI.setPlaceholders(player, PMessage.replace(content[i])));
            }
        }
        return this;
    }

    public Score auto(Player player, int speed) {
        if (player == null) {
            Task.submitAsynchronously(0, speed, run -> {
                refresh(null);
            });
        } else {
            Task.submitAsynchronously(0, speed, run -> {
                if (player == null || !player.isOnline()) {
                    run.cancel();
                }
                int i = content.length - 1;
                for (String teamName : teamNames) {
                    player.getScoreboard().getTeam(teamName).setPrefix(PlaceholderAPI.setPlaceholders(player, PMessage.replace(content[i])));
                    i--;
                }
            });
        }
        return this;
    }

    public Score clear(Player player) {
        player.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
        return this;
    }

    @Override
    public String toYAML() {
        return null;
    }

    @Override
    public String toJSON() {
        return null;
    }

    @Override
    public void send(CommandSender... target) {
        for (CommandSender commandSender : target) {
            Player player = (Player) commandSender;
            player.setScoreboard(scoreboard);
        }
    }

    @Override
    public TextType type() {
        return TextType.SCORE;
    }
}
