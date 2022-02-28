package com.lucas.spectertab;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class Main extends JavaPlugin implements Listener {

	public static Main instance;

	public static Scoreboard SCOREBOARD = Bukkit.getScoreboardManager().getNewScoreboard();

	private void createTeam(String teamName) {
		Scoreboard board = SCOREBOARD;
		if (board.getTeam(teamName) != null)
			board.getTeam(teamName).unregister();
		board.registerNewTeam(teamName);
		board.getTeam(teamName).setPrefix(teamName.substring(2));
	}

	private void loadScoreboards() {
		createTeam("A-§4[Dono] ");
		createTeam("B-§3[Gerente] ");
		createTeam("C-§c[Admin] ");
		createTeam("D-§2[Moderador] ");
		createTeam("E-§e[Ajudante] ");
		createTeam("F-§c[YT] ");
		createTeam("G-§6[Bacon] ");
		createTeam("H-§2[Predador] ");
		createTeam("I-§3[Matador] ");
		createTeam("J-§7");
	}

	@Override
	public void onEnable() {
		instance = this;
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		Bukkit.getConsoleSender().sendMessage(Utilidades.prefixo + "§aPlugin de tab habilitado corretamente.");

		new BukkitRunnable() {

			@Override
			public void run() {
				loadScoreboards();
			}
		}.runTaskTimer(instance, 0L, 100L);
	}

	@Override
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage(Utilidades.prefixo + "§aPlugin de tab desabilitado corretamente.");
	}

	@EventHandler
	public void entrar(PlayerJoinEvent e) {
		Tablist.sendTablist(e.getPlayer(), "§f§lREDE §b§lSPECTER\n  §f§osite",
				"\n             §fAdquira §6§lVIP §fe §b§lRUNAS §facessando: §b§oredespecter.com.br             \n             §fDiscord: §bdiscord.gg/redesecter             ");
		Player a = e.getPlayer();

	}

	@SuppressWarnings("deprecation")
	public static void setTag(Player p, String tag) {
		final Scoreboard board = SCOREBOARD;
		OfflinePlayer offp = Bukkit.getOfflinePlayer(p.getUniqueId());
		for (Team team : board.getTeams()) {
			team.removePlayer(offp);
		}
		board.getTeam(tag).addPlayer(offp);
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(instance, new Runnable() {
			public void run() {
				p.setScoreboard(board);
			}
		}, 5);
	}
}
