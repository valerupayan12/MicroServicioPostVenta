package com.example.MicroPostVenta.contoller;

import java.util.List;

import org.aspectj.apache.bcel.generic.InstructionConstants.Clinit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.MicroPostVenta.model.Cliente;
import com.example.MicroPostVenta.model.Producto;
import com.example.MicroPostVenta.service.ClienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;



@SuppressWarnings("unused")
@RestController
@RequestMapping("api/v2/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    @Operation(summary = "Obtener cliente ",description = "Obtener lista de cliente")
    public List<Cliente> listarClientes(){
        return clienteService.getClientes();
    }
    //agregar
    @PostMapping
    @Operation(summary = "Registrar cliente",description = "Registra cliente existente")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "cliente registrada exitosamente",
                content = @Content(mediaType = "application/JSON",
                    schema = @Schema(implementation = Cliente.class))),
            @ApiResponse(responseCode = "404",description = "cliente no encontrado")
        })
    public Cliente agregarCliente(@Valid @RequestBody Cliente cliente){
        return clienteService.saveClientes(cliente);

    }
    //buscar
    @GetMapping("{id_cliente}")
    @Operation(summary = "Obtener Cliente Por ID",description = "Obtener Lista Cliente ID")
    public Cliente buscraCliente(@PathVariable int id_cliente){
        return clienteService.getClienteById(id_cliente);
    }

    //actualizar
    @PutMapping("{id_cliente}")
    @Operation(summary = "Actualizar Cliente",description = "Actualiza cliente existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "cliente actualizado exitosamente",
            content = @Content(mediaType = "application/JSON",
                schema = @Schema(implementation = Cliente.class))),
        @ApiResponse(responseCode = "404",description = "cliente no encontrado")
    })
    public int actualizarClinte(@PathVariable int id_cliente, @Valid @RequestBody Cliente cliente){
        return clienteService.updateCliente(cliente);
    }

    //eliminar
    @DeleteMapping("{id_cliente}")
     @Operation(summary = "Eliminar cliente",description = "Elimina cliente por id")
    @ApiResponses(value  = {
        @ApiResponse(responseCode = "200", description = "cliente eliminado exitosamente"),
        @ApiResponse(responseCode = "404",description = "cliente no encontrado")
    })
    public String elimiarCliente(@PathVariable int id_clinte){
        if (clienteService.deleteCliente(id_clinte)==1) {
            return "Cliente eliminado correctamente";
        }
        return "Error al eliminar el cliente";
    }

}
