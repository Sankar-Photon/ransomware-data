import {Component, OnInit} from '@angular/core';
import {RansomwareService} from '../ransomware.service';
import {PageEvent} from '@angular/material/paginator';

@Component({   
  selector: 'app-ransomware-table',   
  templateUrl: './ransomware-table.component.html',   
  styleUrls: ['./ransomware-table.component.css']
})

export class RansomwareTableComponent implements OnInit {   
  displayedColumns: string[] = ['name','extensions','ransomNoteFilenames','encryptionAlgorithm','microsoftDetectionName'];   
  dataSource: any[] = [];
  pageIndex = 0;
  pageSize = 10;
  length = 0;   
  constructor(private ransomwareService: RansomwareService) { }   
  ngOnInit(): void {
    this.loadRansomwareData();
  }

  loadRansomwareData(page: number = 0, size: number = 10): void {
    this.ransomwareService.getAllRansomware(page, size).subscribe(data => {
      this.dataSource = data;
      this.length = 411;
    });
  }

  onPageChange(event:PageEvent):void {
    this.pageIndex = event.pageIndex;
    this.pageSize = event.pageSize; 
    this.loadRansomwareData(this.pageIndex, this.pageSize);
  }
}