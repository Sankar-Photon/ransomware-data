import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator'; 
import { HttpClientModule } from '@angular/common/http';
import { RansomwareService } from './ransomware.service';

import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { RansomwareTableComponent } from './ransomware-table/ransomware-table.component';

@NgModule({   
  declarations: [    
    AppComponent, 
    RansomwareTableComponent  
  ],
  imports: [    
    BrowserModule, 
    MatTableModule,
    MatPaginatorModule,
    HttpClientModule
  ],
  providers: [  
    RansomwareService
  ],
  bootstrap: [AppComponent] 
})

export class AppModule { }