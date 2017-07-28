package peanut.medicine.web.Charts;

/**
 * Created by mati on 16.07.17.
 */

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import peanut.medicine.web.admin.AdminStatistics;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.swing.*;
import java.util.Map;


public class ChartsHuj extends ApplicationFrame {

    @Inject
    @Default
    static
    AdminStatistics statistics;

    @PersistenceContext
    private EntityManager em;

    public ChartsHuj( String title ) throws NullPointerException {
        super( title );
        setContentPane(createDemoPanel( ));
    }

   /* private static PieDataset createDataset( ) {
        DefaultPieDataset dataset = new DefaultPieDataset( );
        dataset.setValue(Doctor.class.getName(), new Double(20) );
        dataset.setValue("" , new Double( 20 ) );
        dataset.setValue( "MotoG" , new Double( 40 ) );
        dataset.setValue( "Nokia Lumia" , new Double( 10 ) );
        return dataset;
    }*/


    private static Map <String, Long> createDataset() throws NullPointerException{

/*        List<Survey> surveys = statistics.getAllSurveys();
        List<User> users = statistics.getAllUsers();
        List<Doctor> doctors = statistics.getAllDoctors();
        List<String> specializations = statistics.getAllSpecializations();*/
        Map<String, Long> adminStatistics = statistics.getAdminStatistics();

/*        request.setAttribute("surveys", surveys);
        request.setAttribute("users", users);
        request.setAttribute("doctors", doctors);
        request.setAttribute("specializations", specializations);*/
   /*     request.setAttribute("adminStatistics", adminStatistics);


        request.getRequestDispatcher("admin.jsp").forward(request,response);*/
        return adminStatistics;
    }

    private static JFreeChart createChart(Map <String, Long> dataset ) {
        JFreeChart chart = ChartFactory.createPieChart(
                "Mobile Sales",   // chart title
                (PieDataset) dataset,          // data
                true,             // include legend
                true,
                false);

        return chart;
    }

    public static JPanel createDemoPanel( ) throws NullPointerException{
        JFreeChart chart = createChart(createDataset());
        return new ChartPanel( chart );
    }


    public static void main( String[ ] args ) throws NullPointerException{
        ChartsHuj demo = new ChartsHuj( "Mobile Sales" );
        demo.setSize( 560 , 367 );
        RefineryUtilities.centerFrameOnScreen( demo );
        demo.setVisible( true );
    }
}