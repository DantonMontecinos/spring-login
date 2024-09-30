
package com.example.userloginapp.controller;

import com.example.userloginapp.model.Producto;
import com.example.userloginapp.model.Usuario;
import com.example.userloginapp.service.ProductoService;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/productos")
public class ProductoController {
    
    private final Logger logger = LoggerFactory.getLogger(ProductoController.class);
    
    @Autowired
    private ProductoService productoService;
    
    @GetMapping("")
    public String show(Model model) { //Lleva la vista del backend hacia la vista
        
        model.addAttribute("productos",productoService.findAll());
        
        return "productos/show";
    }
    
      @GetMapping("/create")
    public String crear(){
        
        return "productos/create";
    }
    
    @PostMapping("/save")
    public String save(Producto producto){
        logger.info("Este es objeto producto {}",producto);
        Usuario  u = new Usuario(1,"","","","","","","");
        producto.setUsuario(u);
        productoService.save(producto);
        return "redirect:/productos";
    }
    
    
    
}
