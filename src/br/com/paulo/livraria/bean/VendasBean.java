package br.com.paulo.livraria.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import br.com.paulo.livraria.dao.DAO;
import br.com.paulo.livraria.modelo.Livro;
import br.com.paulo.livraria.modelo.Venda;

@ManagedBean
@ViewScoped
public class VendasBean {

	public BarChartModel getVendasModel() {

		BarChartModel model = new BarChartModel();
		model.setAnimate(true);//coloca o efeito nos valores, tem muita opção no show case do primefaces

		ChartSeries vendaSerie = new ChartSeries();
		vendaSerie.setLabel("Vendas 2020");

		List<Venda> vendas = getVendas(1234);

		for (Venda venda : vendas) {
			vendaSerie.set(venda.getLivro().getTitulo(), venda.getQuantidade());
		}
		
		ChartSeries vendaSerie2019 = new ChartSeries();
		vendaSerie2019.setLabel("Vendas 2019");
		
		 vendas = getVendas(4321);

		for (Venda venda : vendas) {
			vendaSerie2019.set(venda.getLivro().getTitulo(), venda.getQuantidade());
		}
		
		ChartSeries vendaSerie2018 = new ChartSeries();
		vendaSerie2018.setLabel("Vendas 2018");
		
		 vendas = getVendas(1122);

		for (Venda venda : vendas) {
			vendaSerie2018.set(venda.getLivro().getTitulo(), venda.getQuantidade());
		}

		// ChartSeries boys = new ChartSeries();
		// boys.setLabel("Boys");
		// boys.set("2004", 120);
		// boys.set("2005", 100);
		// boys.set("2006", 44);
		// boys.set("2007", 150);
		// boys.set("2008", 25);

		// ChartSeries girls = new ChartSeries();
		// girls.setLabel("Girls");
		// girls.set("2004", 52);
		// girls.set("2005", 60);
		// girls.set("2006", 110);
		// girls.set("2007", 135);
		// girls.set("2008", 120);

		model.addSeries(vendaSerie);
		model.addSeries(vendaSerie2019);
		model.addSeries(vendaSerie2018);
		// model.addSeries(girls);

		return model;

	}

	public List<Venda> getVendas(long seed) {

		List<Livro> livros = new DAO<Livro>(Livro.class).listaTodos();
		List<Venda> vendas = new ArrayList<Venda>();

		Random random = new Random(seed);// gera numeros randomicos
		for (Livro livro : livros) {
			Integer quantidade = random.nextInt(500); // quantidade maxima de
			vendas.add(new Venda(livro, quantidade));
		}

		return vendas;
	}

}
