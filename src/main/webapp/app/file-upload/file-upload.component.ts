import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { FileContent } from 'app/entities/file-content';
import { FileUploadService } from './file-upload.service';
import { ImportResult } from './import-result';

@Component({
  selector: 'jhi-file-upload',
  templateUrl: './file-upload.component.html',
  styleUrls: ['./file-upload.component.scss'],
})
export class FileUploadComponent implements OnInit {
  fileName!: string | any;
  memberUploadFile!: File | any;
  memberErrorMap = new Map<string, string>();
  allFileUploads!: FileContent[];

  @ViewChild(NgForm, { static: true })
  memberFileUploadForm!: NgForm;

  constructor(private fileUploadService: FileUploadService) {}

  ngOnInit(): void {
    this.loadAllFileRecords();
  }

  loadAllFileRecords(): void {
    this.fileUploadService.getAllFileRecords().subscribe(results => {
      console.log(results);
      this.allFileUploads = results;
    });
  }

  onFileSelect(event: any): void {
    this.memberUploadFile = event.target.files[0];
  }

  downloadFile(): void {
    window.location.href = this.fileUploadService.downloadFile();
  }

  onSubmit(): void {
    this.fileUploadService.submitFile(this.memberUploadFile).subscribe(results => {
      this.memberFileUploadForm.resetForm();
      this.memberUploadFile = null;
      this.fileName = null;
    });
  }
}
