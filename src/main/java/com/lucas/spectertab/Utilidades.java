package com.lucas.spectertab;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class Utilidades {

	public static void sendSom(Player p, Sound s) {
		p.playSound(p.getLocation(), s, 1.0F, 0.5F);
	}
	
	public static String prefixo = "§f§lRede§b§lSpecter §8-> §f";
	public static NumberFormat nf = new DecimalFormat("#,##0", new DecimalFormatSymbols(new Locale("pt", "BR")));
	
	public static boolean isInt(String s) {
        try {
            Double.parseDouble(s);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
