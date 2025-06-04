package com.dantesoft.siremono.modules.items.brands.actions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.dantesoft.siremono.internal.commands.CommandExecutor;
import com.dantesoft.siremono.internal.commands.DefaultCommandExecutor;
import com.dantesoft.siremono.modules.items.brands.store.BrandEntity;
import com.dantesoft.siremono.modules.items.brands.store.BrandService;

class ListBrandActionTest {
	private ApplicationContext context;
	private CommandExecutor handler;
    private BrandService brandService;
    private ListBrandAction listBrandAction;
    

    @BeforeEach
    void setUp() {
        brandService = mock(BrandService.class);
        context = mock(ApplicationContext.class);
        
        listBrandAction = new ListBrandAction(brandService);
        handler = new DefaultCommandExecutor(context);
    }

    @Test
    void testDoExecute_ReturnsExpectedOutput() {
        // Arrange
        String searchParam = "sony";
        var pageable = PageRequest.of(0, 10);

        var brand = new BrandEntity( "Sony"); 
        Page<BrandEntity> brandPage = new PageImpl<>(List.of(brand));

        ListBrandInput input = new ListBrandInput();
        input.setSearchParam(searchParam);
        input.setPageable(pageable);
        handler.execute(ListBrandAction.class, input);
        

        when(brandService.searchBrands(searchParam, pageable)).thenReturn(brandPage);

        // Act
        ListBrandOutput output = listBrandAction.doExecute();

        // Assert
        assertNotNull(output);
        assertEquals(1, output.getPayload().getTotalElements());
        assertEquals("Sony", output.getPayload().getContent().get(0).getName());

        // Verifica que el servicio se llamó una sola vez
        verify(brandService, times(1)).searchBrands(searchParam, pageable);
    }
}

