declare type Item = {
  /**
   * A UUID that is generated by the middleware.
   */
  id: string;

  /**
   * A user-supplied name. This is stored in the username column in the database schema.
   */
  name: string;

  /**
   * One of the various AWS SDK languages. Examples include "dotnet", "cpp", and "javascript".
   */
  guide: string;

  /**
   * A plain text description of the job.
   */
  description: string;

  /**
   * A plain text description of the current status of the item.
   */
  status: string;

  /**
   * A Boolean value indicating whether or not the item is archived.
   */
  archived: boolean;
};

export type { Item };
