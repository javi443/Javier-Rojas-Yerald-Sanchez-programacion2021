/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ni.edu.uni.programacion.controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Logger;
import ni.edu.uni.programacion.backend.dao.implementation.JsonVehicleDaoImpl;
import ni.edu.uni.programacion.backend.pojo.VehicleSubModel;
import ni.edu.uni.programacion.views.panels.PnlListVehicles;

/**
 *
 * @author jahp0
 */


public class PnlListVehicleController {
    private Gson gson;
    private JsonVehicleDaoImpl jvdao;
    
    private static final Logger logger = Logger.getLogger(PnlListVehicleController.class.getName());
    
    
    public List<VehicleSubModel> listartodos()
    {
        List<VehicleSubModel> vehicleSubModels;
        gson = new Gson();
//        try {
//       // jvdao = new JsonVehicleDaoImpl();
//        }
//        catch (Exception e) 
//        {
//            logger.severe("Ocurrio un error ".concat(e.toString()) );
//        }
        JsonReader jreader = new JsonReader(new BufferedReader(
                new InputStreamReader(getClass().getResourceAsStream("/jsons/vehicleData.json"))
        ));
        
        Type listType = new TypeToken<ArrayList<VehicleSubModel>>(){}.getType();
        vehicleSubModels = gson.fromJson(jreader, listType);
        
        return vehicleSubModels;
    }
    public List<VehicleSubModel> ListaFiltrada(String filtro)
    {
         List<VehicleSubModel> vehicleSubModels;
         List<VehicleSubModel> listafiltrada = new ArrayList<>();
        gson = new Gson();
//        try {
//        jvdao = new JsonVehicleDaoImpl();
//        }
//        catch (Exception e) 
//        {
//            logger.severe("Ocurrio un error ".concat(e.toString()) );
//        }
        JsonReader jreader = new JsonReader(new BufferedReader(
                new InputStreamReader(getClass().getResourceAsStream("/jsons/vehicleData.json"))
        ));
        
        Type listType = new TypeToken<ArrayList<VehicleSubModel>>(){}.getType();
        vehicleSubModels = gson.fromJson(jreader, listType);
        for (VehicleSubModel i : vehicleSubModels)
        {
            String registro = i.getColor().concat(i.getMake()).concat(i.getModel()).concat(i.getYear());
            logger.info("Viendo el registro: ".concat(registro));
            
            if (registro.contains(filtro))
            {
                logger.info("Hizo match".concat(registro));
                listafiltrada.add(i);
            }
        }
        logger.info("Total de datos: ".concat(Integer.toString(vehicleSubModels.size())));
        logger.info("Se encontraron ".concat(Integer.toString(listafiltrada.size())));
        return listafiltrada;
    }
    
    
}
