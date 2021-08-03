import { Component, OnDestroy, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { ConcernDTO } from '../../models/concern-dto';
import { AdminService } from '../../services/admin.service';
import { DialogBoxComponent } from '../dialog-box/dialog-box.component';

@Component({
  selector: 'app-concerns',
  templateUrl: './concerns.component.html',
  styleUrls: ['./concerns.component.scss']
})
export class ConcernsComponent implements OnInit, OnDestroy {

  subscription: Subscription | undefined;
  id: number = 0;
  concerns: ConcernDTO[] = [];
  warningCount: number = 0;
  blocked: boolean;

  constructor(private _activeRoute: ActivatedRoute, private _adminService: AdminService, private dialog: MatDialog) {
    _activeRoute.params.subscribe(p => {
      this.id = p.id;
      console.log(this.id);
    });
  }

  ngOnInit(): void {
    this.subscription = this._adminService
      .getConcerns(this.id)
      .subscribe((res: any) => {
        this.concerns = res;
        this.concerns.forEach(concern => {
          this.blocked = concern.blocked;
          if (concern.warned == true)
            ++this.warningCount;
        });
      });
  }

  sendWarning(id: number) {
    const confirmDialog = this.dialog.open(DialogBoxComponent, {
      data: {
        title: 'Confirm Send Warning',
        message: 'Are you sure, you want to send warning?'
      }
    });
    confirmDialog.afterClosed().subscribe(result => {
      if (result === true) {
        this._adminService.sendWarning(id).subscribe((res: any) => {
          location.reload();
        });
      }
    });
  }

  unblock() {
    const confirmDialog = this.dialog.open(DialogBoxComponent, {
      data: {
        title: 'Confirm Unblock User',
        message: 'Are you sure, you want to unblock user?'
      }
    });
    confirmDialog.afterClosed().subscribe(result => {
      if (result === true) {
        this._adminService.unblockDoctor(this.id).subscribe((res: any) => {
          location.reload();
        });
      }
    });
  }

  block() {
    const confirmDialog = this.dialog.open(DialogBoxComponent, {
      data: {
        title: 'Confirm Block User',
        message: 'Are you sure, you want to block user?'
      }
    });
    confirmDialog.afterClosed().subscribe(result => {
      if (result === true) {
        this._adminService.blockDoctor(this.id).subscribe((res: any) => {
          location.reload();
        });
      }
    });
  }

  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }
}
